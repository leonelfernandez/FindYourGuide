package edu.uade.ar.findyourguide.service.impl;


import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import edu.uade.ar.findyourguide.repository.UsuarioRepository;
import edu.uade.ar.findyourguide.service.IUsuarioService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioEntity save(UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    @Override
    public List<UsuarioEntity> findAll() {
        return new ArrayList<>(usuarioRepository.findAll());
    }

    @Override
    public Optional<UsuarioEntity> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return usuarioRepository.existsById(id);
    }

    @Override
    public UsuarioEntity partialUpdate(Long id, UsuarioEntity usuarioEntity) {
        usuarioEntity.setId(id);

        return usuarioRepository.findById(id).map(usuario -> {
            Optional.ofNullable(usuarioEntity.getNombre()).ifPresent(usuario::setNombre);
            Optional.ofNullable(usuarioEntity.getApellido()).ifPresent(usuario::setApellido);
            Optional.ofNullable(usuarioEntity.getDni()).ifPresent(usuario::setDni);
            Optional.ofNullable(usuarioEntity.getSexo()).ifPresent(usuario::setSexo);
            Optional.ofNullable(usuarioEntity.getFoto()).ifPresent(usuario::setFoto);
            Optional.ofNullable(usuarioEntity.getEmail()).ifPresent(usuario::setEmail);
            Optional.ofNullable(usuarioEntity.getPassword()).ifPresent(usuario::setPassword);
            Optional.ofNullable(usuarioEntity.getTelefono()).ifPresent(usuario::setTelefono);
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no existe"));
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
