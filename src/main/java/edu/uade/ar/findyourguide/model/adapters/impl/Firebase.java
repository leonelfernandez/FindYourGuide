package edu.uade.ar.findyourguide.model.adapters.impl;

import edu.uade.ar.findyourguide.model.adapters.IAdapterFirebase;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
public class Firebase implements IAdapterFirebase {

    @Override
    public Boolean enviarNotificacion(UsuarioEntity turista, String mensaje) {
        return Boolean.TRUE;
    }
}
