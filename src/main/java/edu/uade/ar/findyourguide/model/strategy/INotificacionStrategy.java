package edu.uade.ar.findyourguide.model.strategy;

import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import edu.uade.ar.findyourguide.model.enums.TipoNotificacionEnum;

public interface INotificacionStrategy {

    public Boolean enviarMensaje(UsuarioEntity turista, String mensaje);

    public TipoNotificacionEnum getTipoNotificacion();

}
