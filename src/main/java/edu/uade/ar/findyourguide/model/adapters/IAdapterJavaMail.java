package edu.uade.ar.findyourguide.model.adapters;

import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;

public interface IAdapterJavaMail {

    public Boolean enviarNotificacion(UsuarioEntity emailDestinatario, String mensaje);

}
