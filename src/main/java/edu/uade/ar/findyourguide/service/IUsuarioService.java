package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<UsuarioEntity> findAll();

    public Optional<UsuarioEntity> findById(Long id);

    public UsuarioEntity save(UsuarioEntity usuario);

    public UsuarioEntity partialUpdate(Long clienteId, UsuarioEntity usuario);

    public void deleteById(Long id);

    public boolean isExists(Long id);


}
