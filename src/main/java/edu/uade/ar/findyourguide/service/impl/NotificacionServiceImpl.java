package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import edu.uade.ar.findyourguide.model.enums.TipoNotificacionEnum;
import edu.uade.ar.findyourguide.model.strategy.INotificacionStrategy;
import edu.uade.ar.findyourguide.service.INotificacionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@AllArgsConstructor
@Data
@Service
public class NotificacionServiceImpl implements INotificacionService {

    private final Map<TipoNotificacionEnum, INotificacionStrategy> notificacionPorTipo;
    private final short diasParaEnviarResenia = 2;

    @Override
    public Boolean enviarNotificacion(UsuarioEntity destinatario, String mensaje, TipoNotificacionEnum tipoNotificacion) {
        INotificacionStrategy notificacionStrategy = this.notificacionPorTipo.get(tipoNotificacion);
        return notificacionStrategy.enviarMensaje(destinatario, mensaje);
    }

    @Override
    public boolean diaParaenviarResenia(TuristaEntity turista) { //Se fija si en el dia de hoy envia la resnia para calificar
        return Boolean.TRUE;
    }
}
