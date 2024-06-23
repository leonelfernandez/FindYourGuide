package edu.uade.ar.findyourguide.config;

import edu.uade.ar.findyourguide.model.enums.TipoNotificacionEnum;
import edu.uade.ar.findyourguide.model.enums.TipoRegistroEnum;
import edu.uade.ar.findyourguide.model.strategy.INotificacionStrategy;
import edu.uade.ar.findyourguide.model.strategy.IRegisterStrategy;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class StrategyRegisterConfig {

    private final List<IRegisterStrategy> registroStrategies;

    @Bean
    public Map<TipoRegistroEnum, IRegisterStrategy> registrarsePorTipo() {
        Map<TipoRegistroEnum, IRegisterStrategy> registroPorTipo = new EnumMap<>(TipoRegistroEnum.class);
        this.registroStrategies.forEach(strategy -> registroPorTipo.put(strategy.getTipoDeRegistro(), strategy));
        return registroPorTipo;
    }

}
