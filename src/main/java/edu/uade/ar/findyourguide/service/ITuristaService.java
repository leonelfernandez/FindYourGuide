package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.CiudadEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;

import java.util.List;
import java.util.Optional;

public interface ITuristaService {

    public List<TuristaEntity> findAll();

    public Optional<TuristaEntity> findById(Long id);

    public TuristaEntity save(TuristaEntity usuario);

    public TuristaEntity partialUpdate(Long clienteId, TuristaEntity usuario);

    public void deleteById(Long id);

    public boolean isExists(Long id);


    public Boolean findByTrofeos(Long id);

    public List<CiudadEntity> findViajesRealizados(TuristaEntity turista);
}
