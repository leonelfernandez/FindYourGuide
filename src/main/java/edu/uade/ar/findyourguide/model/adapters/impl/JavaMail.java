package edu.uade.ar.findyourguide.model.adapters.impl;

import edu.uade.ar.findyourguide.model.adapters.IAdapterJavaMail;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
@Data
public class JavaMail implements IAdapterJavaMail {

    @Override
    public Boolean enviarNotificacion(UsuarioEntity emailDestinatario, String mensaje) {
        return Boolean.TRUE;
    }
}
