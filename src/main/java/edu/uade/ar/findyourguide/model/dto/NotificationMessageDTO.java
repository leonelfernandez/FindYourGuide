package edu.uade.ar.findyourguide.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class NotificationMessageDTO {

    private String verificacionImagenAprobada;
    private String reservaRealizada;
    private String reservaCancelada;
    private String facturaViajeFinalizado;
    private String calificacionGuiaPostViaje;
    private String logroTrofeo;
}
