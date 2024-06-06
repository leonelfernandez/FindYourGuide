package edu.uade.ar.findyourguide.model.adapters.impl;

import edu.uade.ar.findyourguide.model.adapters.IAdapterIAVerificacion;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import org.springframework.stereotype.Component;

@Component
public class IA implements IAdapterIAVerificacion {
    @Override
    public Boolean verificarCredencialGuia(GuiaEntity guia) {
        return guia.getFotoVerificacion() != null;
    }
}
