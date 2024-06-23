package edu.uade.ar.findyourguide.model.strategy.impl;

import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import edu.uade.ar.findyourguide.model.enums.TipoRegistroEnum;
import edu.uade.ar.findyourguide.model.strategy.IRegisterStrategy;
import org.springframework.stereotype.Component;

@Component
public class RegistroTradicionalStrategyImpl implements IRegisterStrategy {
    @Override
    public Boolean registrarse(UsuarioEntity usuario) {
        return Boolean.TRUE;
    }

    @Override
    public TipoRegistroEnum getTipoDeRegistro() {
        return TipoRegistroEnum.TRADICIONAL;
    }
}
