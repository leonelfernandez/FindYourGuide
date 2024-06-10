package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.PagoEntity;

import java.util.List;
import java.util.Optional;

public interface IPagoService {
    public List<PagoEntity> findAll();

    public Optional<PagoEntity> findById(Long id);

    public PagoEntity save(PagoEntity pago);

    public PagoEntity partialUpdate(Long pagoId, PagoEntity pago);

    public void deleteById(Long id);

    public boolean isExists(Long id);


}
