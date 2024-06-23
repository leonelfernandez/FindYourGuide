package edu.uade.ar.findyourguide.model.strategy;

import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import edu.uade.ar.findyourguide.model.enums.TipoNotificacionEnum;
import edu.uade.ar.findyourguide.model.enums.TipoRegistroEnum;

public interface IRegisterStrategy {

    public Boolean registrarse(UsuarioEntity usuario);

    public TipoRegistroEnum getTipoDeRegistro();

}
