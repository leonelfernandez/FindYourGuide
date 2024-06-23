package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.ReseniaEntity;

import java.util.List;
import java.util.Optional;

public interface IReseniaService {

    public List<ReseniaEntity> findAll();

    public Optional<ReseniaEntity> findById(Long id);

    public ReseniaEntity save(ReseniaEntity resenia);

    public ReseniaEntity partialUpdate(Long reseniaId, ReseniaEntity resenia);

    public void deleteById(Long id);

    public boolean isExists(Long id);


    public Iterable<ReseniaEntity> getReseniasByGuia(Long id);
}
