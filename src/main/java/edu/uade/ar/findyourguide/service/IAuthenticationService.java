package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;

import java.util.Optional;

public interface IAuthenticationService {
    public GuiaEntity registrarGuia(GuiaEntity guia);

    public TuristaEntity registrarTurista(TuristaEntity turista);

    public UsuarioEntity login(String email, String password);
}
