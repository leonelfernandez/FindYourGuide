package edu.uade.ar.findyourguide.notification;

import edu.uade.ar.findyourguide.model.dto.UsuarioDTO;
import edu.uade.ar.findyourguide.model.dto.NotificationMessageDTO;

public class MailNotificationStrategy implements INotificationStrategy {

    private NotificationMessageDTO notificationMessageDTO;

    public MailNotificationStrategy(NotificationMessageDTO notificationMessageDTO) {
        this.notificationMessageDTO = notificationMessageDTO;
    }

    @Override
    public void enviarNotification(UsuarioDTO usuario, String tipoMensaje) {
        String mensaje = obtenerMensaje(tipoMensaje);
        usuario.setMensaje(mensaje);

        String asunto = "Notificación Importante";

        // Simulación de envío de notificación por correo
        System.out.println("Enviando notificación por correo al usuario con ID: " + usuario.getId() +
                ", Nombre: " + usuario.getNombre() +
                ", Email: " + usuario.getEmail());
        System.out.println("Asunto: " + asunto);
        System.out.println("Mensaje: " + mensaje);

        boolean envioExitoso = true;

        if (envioExitoso) {
            System.out.println("Notificación por correo enviada exitosamente al usuario con ID: " + usuario.getId());
        } else {
            System.out.println("Error al enviar la notificación por correo al usuario con ID: " + usuario.getId());
        }
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
