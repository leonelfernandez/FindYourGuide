package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EmailStrategy implements INotificadorStrategy {
    @Override
    public void enviarNotificacion(Usuario usuario, String mensaje) {
        // Lógica para enviar la notificación por correo electrónico
        System.out.println("Enviando notificación por correo electrónico a: " + usuario.getEmail());
        System.out.println("Mensaje: " + mensaje);
    }
}
