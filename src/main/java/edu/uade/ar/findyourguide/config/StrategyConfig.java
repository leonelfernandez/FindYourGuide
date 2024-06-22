package edu.uade.ar.findyourguide.config;

import edu.uade.ar.findyourguide.model.enums.TipoNotificacionEnum;
import edu.uade.ar.findyourguide.model.strategy.INotificacionStrategy;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class StrategyConfig {

    private final List<INotificacionStrategy> notificacionStrategies;

    @Bean
    public Map<TipoNotificacionEnum, INotificacionStrategy> enviarNotificacionPorTipo() {
        Map<TipoNotificacionEnum, INotificacionStrategy> mensajesPorTipo = new EnumMap<>(TipoNotificacionEnum.class);
        this.notificacionStrategies.forEach(strategy -> mensajesPorTipo.put(strategy.getTipoNotificacion(), strategy));
        return mensajesPorTipo;
    }
}
