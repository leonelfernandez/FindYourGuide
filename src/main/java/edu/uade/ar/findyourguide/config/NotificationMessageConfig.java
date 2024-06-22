package edu.uade.ar.findyourguide.config;

import edu.uade.ar.findyourguide.model.dto.NotificationMessageDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationMessageConfig {

    @Bean
    public NotificationMessageDTO notificationMessageDTO() {
        return NotificationMessageDTO.builder()
                .verificacionImagenAprobada("Su imagen ha sido verificada y aprobada. Puede comenzar a ofrecer sus servicios.")
                .reservaRealizada("Se ha realizado una reserva. Revise los detalles del turista que hizo la contratación.")
                .reservaCancelada("Su reserva ha sido cancelada. No se le cobrará penalidad alguna y se le reintegrará el dinero.")
                .facturaViajeFinalizado("El viaje ha concluido. Aquí está la factura con el monto total a abonar al guía y los gastos de comisión.")
                .calificacionGuiaPostViaje("Por favor, califique al guía por los servicios prestados.")
                .logroTrofeo("¡Felicitaciones! Ha logrado un nuevo trofeo.")
                .build();
    }
}
