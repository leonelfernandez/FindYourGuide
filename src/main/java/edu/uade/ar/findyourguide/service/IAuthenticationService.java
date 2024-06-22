package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;

public interface IAuthenticationService {
    public GuiaEntity registrarGuia(GuiaEntity guia);
    public TuristaEntity registrarTurista(TuristaEntity turista);

}
