package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Override
    public List<Usuario> findAll() {
        return null;
    }

    @Override
    public Usuario findById(int id) {
        return null;
    }

    @Override
    public void save(Usuario usuario) {

    }

    @Override
    public void update(int clienteId, Usuario usuario) {

    }

    @Override
    public void deleteById(int id) {

    }
}
