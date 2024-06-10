package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class FirebaseStrategy implements INotificadorStrategy {
    @Override
    public void enviarNotificacion(Usuario usuario, String mensaje) {
        // Lógica para enviar la notificación usando Firebase
        System.out.println("Enviando notificación por Firebase a: " + usuario.getFirebaseToken());
        System.out.println("Mensaje: " + mensaje);
    }
}
