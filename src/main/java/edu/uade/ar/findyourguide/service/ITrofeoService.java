package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.ReseniaEntity;
import edu.uade.ar.findyourguide.model.entity.TrofeoEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.model.observer.IObserver;

import java.util.List;
import java.util.Optional;

public interface ITrofeoService {
    public List<TrofeoEntity> findAll();

    public Optional<TrofeoEntity> findById(Long id);

    public TrofeoEntity save(TrofeoEntity reserva);

    public TrofeoEntity partialUpdate(Long reservaId, TrofeoEntity reserva);

    public void deleteById(Long id);

    public boolean isExists(Long id);

    public void evaluarYAsignarTrofeoAlExito(GuiaEntity guia);

    public void evaluarYAsignarTrofeoALaResenia(TuristaEntity turista);
}
