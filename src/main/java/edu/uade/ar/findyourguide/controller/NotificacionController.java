package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.model.entity.Usuario;
import edu.uade.ar.findyourguide.service.INotificacionService;
import edu.uade.ar.findyourguide.service.FirebaseStrategy;
import edu.uade.ar.findyourguide.service.EmailStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private INotificacionService notificacionService;

    @PostMapping("/enviar")
    public void enviarNotificacion(@RequestBody Usuario usuario, @RequestParam String mensaje) {
        notificacionService.enviarNotificacion(usuario, mensaje);
    }

    @PostMapping("/cambiarEstrategia")
    public void cambiarEstrategia(@RequestParam String estrategia) {
        if (estrategia.equals("email")) {
            notificacionService.cambiarEstrategia(new EmailStrategy());
        } else if (estrategia.equals("firebase")) {
            notificacionService.cambiarEstrategia(new FirebaseStrategy());
        }
    }
}
