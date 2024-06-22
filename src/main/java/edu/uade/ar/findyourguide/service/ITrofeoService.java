package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TrofeoEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;

import java.util.List;
import java.util.Optional;

public interface ITrofeoService {
    public List<TrofeoEntity> findAll();

    public Optional<TrofeoEntity> findById(Long id);

    public TrofeoEntity save(TrofeoEntity trofeo);

    public TrofeoEntity partialUpdate(Long trofeoId, TrofeoEntity trofeo);

    public void deleteById(Long id);

    public boolean isExists(Long id);

}