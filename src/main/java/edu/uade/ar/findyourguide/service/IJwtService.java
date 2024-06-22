package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public interface IJwtService {
    public String generateToken(UsuarioEntity usuario);
    }
