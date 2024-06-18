package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.TarifaEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;

import java.util.List;
import java.util.Optional;

public interface ITarifaService {
    public List<TarifaEntity> findAll();

    public Optional<TarifaEntity> findById(Long id);

    public TarifaEntity save(TarifaEntity tarifa);

    public TarifaEntity partialUpdate(Long tarifaId, TarifaEntity tarifa);

    public void deleteById(Long id);

    public boolean isExists(Long id);

}
