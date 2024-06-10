package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;

import java.util.List;
import java.util.Optional;

public interface IReservaService {
    public List<ReservaEntity> findAll();

    public Optional<ReservaEntity> findById(Long id);

    public ReservaEntity save(ReservaEntity reserva);

    public ReservaEntity partialUpdate(Long reservaId, ReservaEntity reserva);

    public void deleteById(Long id);

    public boolean isExists(Long id);


}
