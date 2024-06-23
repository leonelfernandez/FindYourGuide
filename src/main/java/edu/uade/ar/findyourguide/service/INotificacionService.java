package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import edu.uade.ar.findyourguide.model.enums.TipoNotificacionEnum;
import edu.uade.ar.findyourguide.model.strategy.INotificacionStrategy;

public interface INotificacionService {

    public Boolean enviarNotificacion(UsuarioEntity destinatario, String mensaje, TipoNotificacionEnum tipoNotificacion);

    public boolean diaParaenviarResenia(TuristaEntity turista);
}
