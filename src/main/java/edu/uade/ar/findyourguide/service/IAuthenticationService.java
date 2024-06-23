package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import edu.uade.ar.findyourguide.model.enums.TipoRegistroEnum;

public interface IAuthenticationService {
    public GuiaEntity registrarGuia(GuiaEntity guia, TipoRegistroEnum tipoDeRegistro);

    public TuristaEntity registrarTurista(TuristaEntity turista, TipoRegistroEnum tipoDeRegistro);

    public UsuarioEntity login(String email, String password);
}
