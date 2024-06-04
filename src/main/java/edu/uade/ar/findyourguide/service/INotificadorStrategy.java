package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.Usuario;

public interface INotificadorStrategy {
    void enviarNotificacion(Usuario usuario, String mensaje);
}
