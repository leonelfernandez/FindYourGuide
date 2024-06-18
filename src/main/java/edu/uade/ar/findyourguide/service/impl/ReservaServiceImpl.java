package edu.uade.ar.findyourguide.service.impl;


import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReintegroEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.repository.PagoRepository;
import edu.uade.ar.findyourguide.repository.ReintegroRepository;
import edu.uade.ar.findyourguide.repository.ReservaRepository;
import edu.uade.ar.findyourguide.repository.TarifaRepository;
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


    public ReservaServiceImpl(ReservaRepository reservaRepository, PagoRepository pagoRepository, ReintegroRepository reintegroRepository, TarifaRepository tarifaRepository) {
        this.reservaRepository = reservaRepository;
        this.pagoRepository = pagoRepository;
        this.reintegroRepository = reintegroRepository;
        this.tarifaRepository = tarifaRepository;
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
    public ReservaEntity save(ReservaEntity reserva) {
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
    public ReservaEntity cancelarReserva(ReservaEntity reserva, Date fechaCancelacion) {
        try {
            PagoEntity pago = this.pagoRepository.findAll().get(0); //En este momento solo va a haber 1 pago (anticipo)
            reserva.cancelarReserva(fechaCancelacion, pago);
            return reservaRepository.findById(reserva.getId())
                    .orElseThrow(() -> new NoSuchElementException("Reserva no existe"));
        } catch (NoSuchElementException e) {
            reserva.cancelarReserva(fechaCancelacion, null);
            return reservaRepository.findById(reserva.getId())
                    .orElseThrow(() -> new NoSuchElementException("Reserva no existe"));
        }

    }

    @Override
    public ReservaEntity rechazarReserva(ReservaEntity reserva, Date fechaCancelacion) {
        try {
            PagoEntity pago = reserva.getPagos().get(0);
            reintegroRepository.save(new ReintegroEntity(pago.getMontoAReintegrar(), fechaCancelacion,pago));
            reserva.rechazarReserva();
            return reservaRepository.findById(reserva.getId())
                    .orElseThrow(() -> new NoSuchElementException("Reserva no existe"));
        } catch(NoSuchElementException e) {
            return reservaRepository.findById(reserva.getId())
                    .orElseThrow(() -> new NoSuchElementException("Reserva no existe"));
        }
    }

    public Float calcularMontoTotal(ReservaEntity reserva) {
        Long guiaId = reserva.getGuia().getId();
        Long ciudadID = reserva.getCiudad().getId();
        return reservaRepository.findMontoTotalReserva(ciudadID, guiaId);
    }

    @Override
    public Float calcularMontoAnticipo(ReservaEntity reserva) {
        return calcularMontoTotal(reserva) * porcentajeAnticipo;
    }


}
