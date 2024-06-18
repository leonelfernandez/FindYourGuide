package edu.uade.ar.findyourguide.service.impl;


import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.repository.PagoRepository;
import edu.uade.ar.findyourguide.repository.ReintegroRepository;
import edu.uade.ar.findyourguide.repository.ReservaRepository;
import edu.uade.ar.findyourguide.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservaServiceImpl implements IReservaService {

    private ReservaRepository reservaRepository;

    private PagoRepository pagoRepository;

    private ReintegroRepository reintegroRepository;


    public ReservaServiceImpl(ReservaRepository reservaRepository, PagoRepository pagoRepository, ReintegroRepository reintegroRepository) {
        this.reservaRepository = reservaRepository;
        this.pagoRepository = pagoRepository;
        this.reintegroRepository = reintegroRepository;
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
            Optional.ofNullable(reservaEntity.getViaje()).ifPresent(reserva::setViaje);;
            Optional.ofNullable(reservaEntity.getServiciosContratados()).ifPresent(reserva::setServiciosContratados);;
            Optional.ofNullable(reservaEntity.getTarifaServicio()).ifPresent(reserva::setTarifaServicio);
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
                    .orElseThrow(() -> new RuntimeException("Reserva no existe"));
        } catch (NoSuchElementException e) {
            reserva.cancelarReserva(fechaCancelacion, null);
            return reservaRepository.findById(reserva.getId())
                    .orElseThrow(() -> new RuntimeException("Reserva no existe"));
        }

    }
}
