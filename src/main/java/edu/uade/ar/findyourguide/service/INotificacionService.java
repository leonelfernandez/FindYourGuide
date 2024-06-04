package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.Usuario;

public interface INotificacionService {
    void enviarNotificacion(Usuario usuario, String mensaje);
    void cambiarEstrategia(INotificadorStrategy estrategia);
}
