package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.adapters.IAdapterIAVerificacion;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.service.IVerificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificacionServiceImpl implements IVerificacionService {

    IAdapterIAVerificacion adapterVerificacion;

    @Autowired
    public VerificacionServiceImpl(IAdapterIAVerificacion adapterVerificacion) {
        this.adapterVerificacion = adapterVerificacion;
    }

    @Override
    public Boolean verificarCredencialGuia(GuiaEntity guia) {
        return adapterVerificacion.verificarCredencialGuia(guia);
    }
}
