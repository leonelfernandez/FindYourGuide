package edu.uade.ar.findyourguide.service.impl;


import edu.uade.ar.findyourguide.exceptions.*;
import edu.uade.ar.findyourguide.model.adapters.IPagoAdapter;
import edu.uade.ar.findyourguide.model.adapters.impl.Stripe;
import edu.uade.ar.findyourguide.model.entity.*;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
import edu.uade.ar.findyourguide.model.enums.TipoPagoEnum;
import edu.uade.ar.findyourguide.repository.*;
import edu.uade.ar.findyourguide.service.IReservaService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservaServiceImpl implements IReservaService {

    private ReservaRepository reservaRepository;

    private PagoRepository pagoRepository;

    private ReintegroRepository reintegroRepository;

    private TarifaRepository tarifaRepository;

    private Float porcentajeAnticipo = 0.10F;

    private IPagoAdapter pagoAdapter;

    private Float montoPenalizacion = 0.20F;
    private ServicioRepository servicioRepository;


    public ReservaServiceImpl(ReservaRepository reservaRepository, PagoRepository pagoRepository, ReintegroRepository reintegroRepository, TarifaRepository tarifaRepository, ServicioRepository servicioRepository) {
        this.reservaRepository = reservaRepository;
        this.pagoRepository = pagoRepository;
        this.reintegroRepository = reintegroRepository;
        this.tarifaRepository = tarifaRepository;
        this.pagoAdapter = new Stripe();
        this.servicioRepository = servicioRepository;
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
        List<ServicioEntity> serviciosRepo = servicioRepository.findAll();
        List<ServicioEntity> filteredServiciosRepo = serviciosRepo.stream()
                .filter(reserva.getServiciosContratados()::contains)
                .toList();
        reserva.setServiciosContratados(filteredServiciosRepo);
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
    public ReservaEntity cancelarReserva(ReservaEntity reserva, Date fechaCancelacion) throws ReservaFinalizadaError {
        try {
            List<PagoEntity> pagos = reserva.getPagos(); //Obtener pagos de la reserva
            if (verificarFechaCancelacion(reserva, fechaCancelacion) && pagos.size() == 1) { //ESTO ME DA MIEDO -- Cancelacion en fecha de viaje (Estado Reservado)
                PagoEntity pago = new PagoEntity(this.calcularMontoRestante(reserva), fechaCancelacion, reserva, TipoPagoEnum.PENALIZACION);
                reserva.agregarPago(pago);
                pagoRepository.save(pago);
                this.pagoAdapter.realizarPago(this.calcularMontoRestante(reserva));
                reserva.cancelarReserva(fechaCancelacion);
            } else if (pagos.size() == 1 && reserva.getEstado() == ReservaStateEnum.RESERVADO) {
                PagoEntity pago = new PagoEntity(this.calcularMontoRestante(reserva) * this.montoPenalizacion, fechaCancelacion, reserva, TipoPagoEnum.PENALIZACION);
                reserva.agregarPago(pago);
                pagoRepository.save(pago);
                this.pagoAdapter.realizarPago(this.calcularMontoTotal(reserva) * this.montoPenalizacion);
                reserva.cancelarReserva(fechaCancelacion);
            } else {
                reserva.cancelarReserva(fechaCancelacion);
            }
            return reservaRepository.save(reserva);
            } catch (ReservaFinalizadaError e) {
            throw new RuntimeException(e);
            }
    }

    private Boolean verificarFechaCancelacion(ReservaEntity reserva, Date fechaCancelacion) {
        return reservaRepository.fechaCancelacionEnViaje(reserva.getId(), fechaCancelacion) != null;
    }

    @Override
    public ReservaEntity rechazarReserva(ReservaEntity reserva, Date fechaCancelacion) {
//        try {
//            PagoEntity pago = reserva.getPagos().get(0);
//            reintegroRepository.save(new ReintegroEntity(pago.getMontoAReintegrar(), fechaCancelacion,pago));
//            reserva.rechazarReserva();
//            return reservaRepository.findById(reserva.getId())
//                    .orElseThrow(() -> new NoSuchElementException("Reserva no existe"));
//        } catch(NoSuchElementException e) {
//            return reservaRepository.findById(reserva.getId())
//                    .orElseThrow(() -> new NoSuchElementException("Reserva no existe"));
//        }
        return null;
    }

    @Override
    public ReservaEntity pagar(PagoEntity pago) throws PagosYaRealizadosError, AnticipoPagadoError, ReservaFinalizadaError {
        ReservaEntity reservaGuardada = reservaRepository.findById(pago.getReserva().getId()).orElseThrow(() -> new NoSuchElementException("Reserva no existe"));
        List<PagoEntity> pagosRealizados = reservaGuardada.getPagos();
        if (pagosRealizados.size() == 2)
            throw new PagosYaRealizadosError("Pagos ya realizados para esta reserva");
        if (pagosRealizados.isEmpty())
            this.pagarAnticipo(reservaGuardada);
        else
            this.pagarRestante(reservaGuardada);

        reservaGuardada.pagar(pago);
        reservaGuardada.agregarPago(pago);
        return reservaRepository.save(reservaGuardada);

    }

    @Override
    public ReservaEntity confirmarReserva(ReservaEntity reserva) throws PagoNoRealizadoError, ReservaConfirmadaError, ReservaFinalizadaError {
        reserva.confirmarReserva();
        return reservaRepository.save(reserva);
    }

    private void pagarAnticipo(ReservaEntity reserva) {
        this.pagoAdapter.realizarPago(this.calcularMontoAnticipo(reserva));
    }

    private void pagarRestante(ReservaEntity reserva) {
        this.pagoAdapter.realizarPago(this.calcularMontoTotal(reserva) - this.calcularMontoAnticipo(reserva));
    }

    private Float getMontoAReintegrar(PagoEntity pago) {
        return pago.getMontoTotal() * this.porcentajeAnticipo;
    }
    @Override
    public Float calcularMontoTotal(ReservaEntity reserva) {
        Long guiaId = reserva.getGuia().getId();
        Long ciudadID = reserva.getCiudad().getId();
        return reservaRepository.findMontoTotalReserva(ciudadID, guiaId);
    }

    @Override
    public Float calcularMontoRestante(ReservaEntity reserva) {
        return calcularMontoTotal(reserva) - calcularMontoAnticipo(reserva);
    }

    @Override
    public Float calcularMontoAnticipo(ReservaEntity reserva) {
        return calcularMontoTotal(reserva) * porcentajeAnticipo;
    }

    @Override
    public void realizarReintegro(PagoEntity pago, Date fechaCancelacion) {
        this.reintegroRepository.save(new ReintegroEntity(this.getMontoAReintegrar(pago), fechaCancelacion, pago));
    }



}
