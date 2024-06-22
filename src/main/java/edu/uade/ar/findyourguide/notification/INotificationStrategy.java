package edu.uade.ar.findyourguide.notification;

import edu.uade.ar.findyourguide.model.dto.UsuarioDTO;

public interface INotificationStrategy {
    void enviarNotification(UsuarioDTO usuario, String mensaje);
}
