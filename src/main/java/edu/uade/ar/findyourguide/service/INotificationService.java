package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.dto.UsuarioDTO;

public interface INotificationService {
    void enviarNotification(UsuarioDTO usuario, String mensaje);
}
