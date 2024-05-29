package edu.uade.ar.findyourguide.model.dao;

import edu.uade.ar.findyourguide.model.entity.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario>  {
    public UsuarioDAO(Class<Usuario> clase, String file) throws Exception {
        super(clase, file);
    }
}
