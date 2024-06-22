package edu.uade.ar.findyourguide.model.adapters.impl;

import edu.uade.ar.findyourguide.model.adapters.IAdapterIAVerificacion;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class IA implements IAdapterIAVerificacion {
    @Override
    public Boolean verificarCredencialGuia(GuiaEntity guia) {
        return guia.getFotoVerificacion() != null;
    }
}
