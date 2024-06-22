package edu.uade.ar.findyourguide.model.adapters;

import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;

public interface IAdapterFirebase {
    public Boolean enviarNotificacion(UsuarioEntity turista, String mensaje);
}
