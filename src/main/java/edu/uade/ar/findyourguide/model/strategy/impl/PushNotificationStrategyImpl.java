package edu.uade.ar.findyourguide.model.strategy.impl;

import edu.uade.ar.findyourguide.model.adapters.IAdapterFirebase;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import edu.uade.ar.findyourguide.model.enums.TipoNotificacionEnum;
import edu.uade.ar.findyourguide.model.strategy.INotificacionStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushNotificationStrategyImpl implements INotificacionStrategy {

    @Autowired
    IAdapterFirebase adapterFirebase;

    @Override
    public Boolean enviarMensaje(UsuarioEntity turista, String mensaje) {
        return adapterFirebase.enviarNotificacion(turista, mensaje);
    }

    @Override
    public TipoNotificacionEnum getTipoNotificacion() {
        return TipoNotificacionEnum.PUSH_NOTIFICATION;
    }
}
