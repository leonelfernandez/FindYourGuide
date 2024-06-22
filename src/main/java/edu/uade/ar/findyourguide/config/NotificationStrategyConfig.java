package edu.uade.ar.findyourguide.config;

import edu.uade.ar.findyourguide.notification.INotificationStrategy;
import edu.uade.ar.findyourguide.notification.MailNotificationStrategy;
import edu.uade.ar.findyourguide.notification.PushNotificationStrategy;
import edu.uade.ar.findyourguide.model.dto.NotificationMessageDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationStrategyConfig {

    private final NotificationMessageDTO notificationMessageDTO;

    public NotificationStrategyConfig(NotificationMessageDTO notificationMessageDTO) {
        this.notificationMessageDTO = notificationMessageDTO;
    }

    @Bean
    public INotificationStrategy notificationStrategy() {
        // Aquí seleccionamos la estrategia que queremos utilizar
        return new PushNotificationStrategy(notificationMessageDTO); // o new MailNotificationStrategy(notificationMessageDTO)
    }
}
