package edu.uade.ar.findyourguide.model.strategy.impl;

import edu.uade.ar.findyourguide.model.adapters.IAdapterJavaMail;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import edu.uade.ar.findyourguide.model.enums.TipoNotificacionEnum;
import edu.uade.ar.findyourguide.model.strategy.INotificacionStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnvioMailStrategyImpl implements INotificacionStrategy {

    @Autowired
    private IAdapterJavaMail adapterJavaMail;

    @Override
    public Boolean enviarMensaje(UsuarioEntity turista, String mensaje) {
        return adapterJavaMail.enviarNotificacion(turista, mensaje);
    }

    @Override
    public TipoNotificacionEnum getTipoNotificacion() {
        return TipoNotificacionEnum.EMAIL;
    }
}
