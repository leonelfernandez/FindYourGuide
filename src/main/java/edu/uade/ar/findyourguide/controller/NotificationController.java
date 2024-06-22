package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.model.dto.UsuarioDTO;
import edu.uade.ar.findyourguide.service.impl.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationServiceImpl notificationService;

    @Autowired
    public NotificationController(NotificationServiceImpl notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<UsuarioDTO> sendNotification(@RequestBody UsuarioDTO usuario, @RequestParam String tipoMensaje) {
        return notificationService.enviarYResponderNotification(usuario, tipoMensaje);
    }
}
