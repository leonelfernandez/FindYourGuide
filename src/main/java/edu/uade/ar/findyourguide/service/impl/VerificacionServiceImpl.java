package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.adapters.IAdapterIAVerificacion;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.service.IVerificacionService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class VerificacionServiceImpl implements IVerificacionService {


    IAdapterIAVerificacion adapterVerificacion;

    public VerificacionServiceImpl(IAdapterIAVerificacion adapterVerificacion) {
        this.adapterVerificacion = adapterVerificacion;
    }


    @Override
    public Boolean verificarCredencialGuia(GuiaEntity guia) {
        return adapterVerificacion.verificarCredencialGuia(guia);
    }
}
