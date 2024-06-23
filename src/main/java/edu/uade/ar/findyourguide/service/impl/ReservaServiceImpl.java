package edu.uade.ar.findyourguide.service.impl;


import edu.uade.ar.findyourguide.exceptions.*;
import edu.uade.ar.findyourguide.model.adapters.IPagoAdapter;
import edu.uade.ar.findyourguide.model.entity.*;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
import edu.uade.ar.findyourguide.model.enums.TipoPagoEnum;
import edu.uade.ar.findyourguide.repository.*;
import edu.uade.ar.findyourguide.service.IReservaService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservaServiceImpl implements IReservaService {

    private final GuiaRepository guiaRepository;
    private final FacturaRepository facturaRepository;
    private ReservaRepository reservaRepository;

    private PagoRepository pagoRepository;

    private ReintegroRepository reintegroRepository;

    private TarifaRepository tarifaRepository;

    private Float porcentajeAnticipo;

    private IPagoAdapter pagoAdapter;

    private Float montoPenalizacion;

    private Float montoRecargoPlataforma;

    private ServicioRepository servicioRepository;

    private CiudadRepository ciudadRepository;


    public ReservaServiceImpl(ReservaRepository reservaRepository, PagoRepository pagoRepository, ReintegroRepository reintegroRepository, TarifaRepository tarifaRepository, IPagoAdapter pagoAdapter, ServicioRepository servicioRepository, CiudadRepository ciudadRepository, GuiaRepository guiaRepository, FacturaRepository facturaRepository) {
        this.reservaRepository = reservaRepository;
        this.pagoRepository = pagoRepository;
        this.reintegroRepository = reintegroRepository;
        this.tarifaRepository = tarifaRepository;
        this.porcentajeAnticipo = 0.10F;
        this.pagoAdapter = pagoAdapter;
        this.montoPenalizacion = 0.20F;
        this.servicioRepository = servicioRepository;
        this.ciudadRepository = ciudadRepository;
        this.guiaRepository = guiaRepository;
        this.montoRecargoPlataforma = 0.15F;
        this.facturaRepository = facturaRepository;
    }

    @Override
    public List<ReservaEntity> findAll() {
        return new ArrayList<>(reservaRepository.findAll());
    }

    @Override
    public Optional<ReservaEntity> findById(Long id) {
        return reservaRepository.findById(id);
    }


    @Override
    public ReservaEntity save(ReservaEntity reserva) throws ReservaError {
        if (reservaRepository.countOverlapping(reserva.getId(), reserva.getFechaInicio(), reserva.getFechaFin(), ReservaStateEnum.PENDIENTE, ReservaStateEnum.CONFIRMADO, ReservaStateEnum.RESERVADO) > 0) {
            throw new ReservaError("El turista ya tiene una reserva en esa fecha");
        }
        GuiaEntity guia = guiaRepository.findById(reserva.getGuia().getId()).orElseThrow(() -> new NoSuchElementException("Guia no existe"));
        List<ServicioEntity> serviciosGuia = guia.getServiciosOfrecidos();
        List<ServicioEntity> serviciosContratados = reserva.getServiciosContratados();
        if (new HashSet<>(serviciosGuia).containsAll(serviciosContratados)) {
            List<ServicioEntity> serviciosRepo = servicioRepository.findAll();
            List<ServicioEntity> filteredServiciosRepo = serviciosRepo.stream()
                    .filter(reserva.getServiciosContratados()::contains)
                    .toList();
            reserva.setServiciosContratados(filteredServiciosRepo);
        } else {
            throw new ReservaError("El guia no ofrece todos los servicios contratados");
        }
        facturaRepository.save(new FacturaEntity(TipoPagoEnum.ANTICIPO, this.calcularMontoAnticipo(guia, reserva.getCiudad().getId()), reserva.getFechaReservaIniciada()));
        return reservaRepository.save(reserva);
    }


    @Override
    public ReservaEntity partialUpdate(Long reservaId, ReservaEntity reservaEntity) {
        reservaEntity.setId(reservaId);

        return reservaRepository.findById(reservaId).map(reserva -> {
            Optional.ofNullable(reservaEntity.getTurista()).ifPresent(reserva::setTurista);
            Optional.ofNullable(reservaEntity.getGuia()).ifPresent(reserva::setGuia);
            Optional.ofNullable(reservaEntity.getEstado()).ifPresent(reserva::setEstado);
            Optional.ofNullable(reservaEntity.getPagos()).ifPresent(reserva::setPagos);
            Optional.ofNullable(reservaEntity.getServiciosContratados()).ifPresent(reserva::setServiciosContratados);;
            return reservaRepository.save(reserva);
        }).orElseThrow(() -> new RuntimeException("Reserva no existe"));
    }


    @Override
    public void deleteById(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return reservaRepository.existsById(id);
    }


    @Override
    public ReservaEntity cancelarReserva(ReservaEntity reserva, Date fechaCancelacion) throws ReservaFinalizadaError, ReservaRechazadaError, CancelarError {
        try {
            List<PagoEntity> pagos = reserva.getPagos();
            if (verificarFechaCancelacion(reserva, fechaCancelacion) && pagos.size() == 1 && reserva.getEstado() == ReservaStateEnum.RESERVADO) {
                Float monto = this.calcularMontoRestante(reserva.getGuia(), reserva.getCiudad().getId()) * (1+this.montoPenalizacion);
                facturaRepository.save(new FacturaEntity(TipoPagoEnum.PENALIZACION, monto, fechaCancelacion));
                reserva.cancelarReserva(fechaCancelacion);
            } else if ( pagos.size() == 1 && reserva.getEstado() == ReservaStateEnum.RESERVADO){
                Float monto = this.calcularMontoRestante(reserva.getGuia(), reserva.getCiudad().getId()) * this.montoPenalizacion;
                facturaRepository.save(new FacturaEntity(TipoPagoEnum.PENALIZACION, monto, fechaCancelacion));
                reserva.cancelarReserva(fechaCancelacion);
            } else {
                reserva.cancelarReserva(fechaCancelacion);
            }
            return reservaRepository.save(reserva);
            } catch (ReservaFinalizadaError e) {
                throw new ReservaFinalizadaError("Reserva finalizada, no se puede cancelar");
            } catch (ReservaRechazadaError e) {
                throw new ReservaRechazadaError("Reserva rechazada, no se puede cancelar");
            } catch (CancelarError e) {
                throw new CancelarError("Error al cancelar la reserva");
            }
    }

    private Boolean verificarFechaCancelacion(ReservaEntity reserva, Date fechaCancelacion) {
        return reservaRepository.fechaCancelacionEnViaje(reserva.getId(), fechaCancelacion) != null;
    }

    @Override
    public ReservaEntity rechazarReserva(ReservaEntity reserva, Date fechaCancelacion) throws PagoNoRealizadoError, ReservaConfirmadaError, ReservaFinalizadaError, ReservaRechazadaError {
        try {
            if (reserva.getEstado() == ReservaStateEnum.CONFIRMADO) {
                PagoEntity pago = reserva.getPagos().getFirst();
                ReintegroEntity reintegro = new ReintegroEntity(this.calcularMontoAnticipo(reserva.getGuia(), reserva.getCiudad().getId()), fechaCancelacion, reserva.getPagos().getFirst());
                reintegroRepository.save(reintegro);
                pago.getReintegro().add(reintegro);
                pagoRepository.save(pago);
            }
            reserva.rechazarReserva();
            return reservaRepository.save(reserva);
        } catch(NoSuchElementException e) {
            return null;
        } catch (PagoNoRealizadoError e) {
            throw new PagoNoRealizadoError("Pago no realizado");
        } catch (ReservaConfirmadaError e) {
            throw new ReservaConfirmadaError("Reserva confirmada, no se puede rechazar");
        } catch (ReservaFinalizadaError e) {
            throw new ReservaFinalizadaError("Reserva finalizada, no se puede rechazar");
        } catch (ReservaRechazadaError e) {
            throw new ReservaRechazadaError("Reserva rechazada, no se puede rechazar");
        }
    }

    @Override
    public ReservaEntity pagar(PagoEntity pago) throws PagosYaRealizadosError, AnticipoPagadoError, ReservaFinalizadaError, ReservaRechazadaError {
        ReservaEntity reservaGuardada = reservaRepository.findById(pago.getReserva().getId()).orElseThrow(() -> new NoSuchElementException("Reserva no existe"));
        List<FacturaEntity> facturas = facturaRepository.findAll().stream().filter(factura -> Objects.equals(factura.getId(), pago.getFactura().getId())).toList();
        if (facturas.size() == 1 && pago.getFactura().getDetalle() == TipoPagoEnum.ANTICIPO) {
            this.pagarAnticipo(reservaGuardada);
            reservaGuardada.pagar(pago);
        } else if(facturas.size() == 1 && pago.getFactura().getDetalle() == TipoPagoEnum.PENALIZACION) {
            this.pagarRestante(reservaGuardada);
        } else if (pago.getFactura().getDetalle() == TipoPagoEnum.TOTAL && reservaGuardada.getEstado() == ReservaStateEnum.FINALIZADO){
            this.pagarRestante(reservaGuardada);
        }

        reservaGuardada.agregarPago(pago);
        return reservaRepository.save(reservaGuardada);

    }


    @Override
    public ReservaEntity confirmarReserva(ReservaEntity reserva) throws PagoNoRealizadoError, ReservaConfirmadaError, ReservaFinalizadaError, ReservaRechazadaError {
        reserva.confirmarReserva();
        return reservaRepository.save(reserva);
    }

    private void pagarAnticipo(ReservaEntity reserva) {
        this.pagoAdapter.realizarPago(this.calcularMontoAnticipo(reserva.getGuia(), reserva.getCiudad().getId()));
    }

    private void pagarRestante(ReservaEntity reserva) {
        this.pagoAdapter.realizarPago(this.calcularMontoRestante(reserva.getGuia(), reserva.getCiudad().getId()) - this.calcularMontoAnticipo(reserva.getGuia(), reserva.getCiudad().getId()));
    }

    private Float getMontoAReintegrar(PagoEntity pago) {
        return pago.getMontoTotal() * this.porcentajeAnticipo;
    }
    @Override
    public Float calcularMontoTotal(GuiaEntity guia, Long ciudadDestinoId) {
        return tarifaRepository.findMontoTotalReserva(guia.getId(), ciudadDestinoId) * (1.0F + this.montoRecargoPlataforma);
    }

    @Override
    public Float calcularComisionDePlataforma(GuiaEntity guia, Long ciudadDestinoId) {
        return tarifaRepository.findMontoTotalReserva(guia.getId(), ciudadDestinoId) * this.montoRecargoPlataforma;
    }

    @Override
    public Float calcularMontoRestante(GuiaEntity guia, Long ciudadDestinoId) {
        return tarifaRepository.findMontoTotalReserva(guia.getId(), ciudadDestinoId) - (tarifaRepository.findMontoTotalReserva(guia.getId(), ciudadDestinoId) * porcentajeAnticipo);
    }

    @Override
    public Float calcularMontoAnticipo(GuiaEntity guia, Long ciudadDestinoId) {
        return tarifaRepository.findMontoTotalReserva(guia.getId(), ciudadDestinoId) * porcentajeAnticipo;
    }

    @Override
    public void realizarReintegro(PagoEntity pago, Date fechaCancelacion) {
        this.reintegroRepository.save(new ReintegroEntity(this.getMontoAReintegrar(pago), fechaCancelacion, pago));
    }

    @Override
    public Iterable<ReservaEntity> getReservasFinalizadasByGuia(Long id) {
        return reservaRepository.getReservasFinalizadasByGuia(id);
    }

    @Override
    public Iterable<CiudadEntity> getAllCiudadesIn(List<Long> ids) {
        return ciudadRepository.getAllCiudadesById(ids);
    }

    @Override
    public ReservaEntity finalizarReserva(ReservaEntity reserva) throws FinalizadoError {
        reserva.finalizarReserva();
        ReservaEntity reservaGuardada = reservaRepository.save(reserva);
        ReservaEntity resultado = reservaRepository.findById(reservaGuardada.getId()).get();
        if (resultado.getEstado() == ReservaStateEnum.FINALIZADO) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(resultado.getFechaFin());
            calendar.add(Calendar.DATE, 5);
            Date newFechaFin = calendar.getTime();
            facturaRepository.save(new FacturaEntity(TipoPagoEnum.TOTAL, this.calcularMontoRestante(resultado.getGuia(), resultado.getCiudad().getId()) * (1 + this.montoRecargoPlataforma), newFechaFin));
        }
        return reservaRepository.save(reserva);

    }

}
