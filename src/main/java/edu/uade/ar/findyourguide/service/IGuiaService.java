package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface IGuiaService {
    public List<GuiaEntity> findAll();

    public Optional<GuiaEntity> findById(Long id);

    public GuiaEntity save(GuiaEntity usuario);

    public GuiaEntity partialUpdate(Long clienteId, GuiaEntity usuario);

    public void deleteById(Long id);

    public boolean isExists(Long id);


}
