package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> findAll();

    public Usuario findById(int id);

    public void save(Usuario usuario);

    public void update(int clienteId, Usuario usuario);

    public void deleteById(int id);


}
