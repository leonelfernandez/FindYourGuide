package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacionServiceImpl implements INotificacionService {

    private INotificadorStrategy estrategia;

    @Autowired
    public NotificacionServiceImpl(INotificadorStrategy estrategia) {
        this.estrategia = estrategia;
    }

    @Override
    public void enviarNotificacion(Usuario usuario, String mensaje) {
        estrategia.enviarNotificacion(usuario, mensaje);
    }

    @Override
    public void cambiarEstrategia(INotificadorStrategy nuevaEstrategia) {
        this.estrategia = nuevaEstrategia;
    }
}
