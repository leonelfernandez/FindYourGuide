package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.dto.NotificationMessageDTO;
import edu.uade.ar.findyourguide.model.dto.UsuarioDTO;
import edu.uade.ar.findyourguide.notification.INotificationStrategy;
import edu.uade.ar.findyourguide.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements INotificationService {

    private final INotificationStrategy notificationStrategy;
    private final NotificationMessageDTO notificationMessageDTO;

    @Autowired
    public NotificationServiceImpl(INotificationStrategy notificationStrategy, NotificationMessageDTO notificationMessageDTO) {
        this.notificationStrategy = notificationStrategy;
        this.notificationMessageDTO = notificationMessageDTO;
    }

    @Override
    public void enviarNotification(UsuarioDTO usuario, String tipoMensaje) {
        // La estrategia se encarga de establecer el mensaje adecuado
        notificationStrategy.enviarNotification(usuario, tipoMensaje);
    }

    public ResponseEntity<UsuarioDTO> enviarYResponderNotification(UsuarioDTO usuario, String tipoMensaje) {
        // Agregar el mensaje al UsuarioDTO
        String mensaje = obtenerMensaje(tipoMensaje);
        usuario.setMensaje(mensaje);

        // Enviar la notificación
        notificationStrategy.enviarNotification(usuario, tipoMensaje);

        // Devolver el UsuarioDTO en un ResponseEntity
        return ResponseEntity.ok(usuario);
    }

    private String obtenerMensaje(String tipoMensaje) {
        switch (tipoMensaje) {
            case "verificacionImagenAprobada":
                return notificationMessageDTO.getVerificacionImagenAprobada();
            case "reservaRealizada":
                return notificationMessageDTO.getReservaRealizada();
            case "reservaCancelada":
                return notificationMessageDTO.getReservaCancelada();
            case "facturaViajeFinalizado":
                return notificationMessageDTO.getFacturaViajeFinalizado();
            case "calificacionGuiaPostViaje":
                return notificationMessageDTO.getCalificacionGuiaPostViaje();
            case "logroTrofeo":
                return notificationMessageDTO.getLogroTrofeo();
            default:
                return "Mensaje no especificado";
        }
    }
}
