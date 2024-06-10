package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.repository.TuristaRepository;
import edu.uade.ar.findyourguide.service.ITuristaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TuristaServiceImpl implements ITuristaService {

    private TuristaRepository turistaRepository;

    public TuristaServiceImpl(TuristaRepository turistaRepository) {
        this.turistaRepository = turistaRepository;
    }

    @Override
    public List<TuristaEntity> findAll() {
        return new ArrayList<>(turistaRepository.findAll());
    }

    @Override
    public Optional<TuristaEntity> findById(Long id) {
        return turistaRepository.findById(id);
    }

    @Override
    public TuristaEntity save(TuristaEntity usuario) {
        return turistaRepository.save(usuario);
    }

    @Override
    public TuristaEntity partialUpdate(Long clienteId, TuristaEntity turistaEntity) {
        turistaEntity.setId(clienteId);

        return turistaRepository.findById(clienteId).map(usuario -> {
            Optional.ofNullable(turistaEntity.getNombre()).ifPresent(usuario::setNombre);
            Optional.ofNullable(turistaEntity.getApellido()).ifPresent(usuario::setApellido);
            Optional.ofNullable(turistaEntity.getSexo()).ifPresent(usuario::setSexo);
            Optional.ofNullable(turistaEntity.getDni()).ifPresent(usuario::setDni);
            Optional.ofNullable(turistaEntity.getEmail()).ifPresent(usuario::setEmail);
            Optional.ofNullable(turistaEntity.getPassword()).ifPresent(usuario::setPassword);
            Optional.ofNullable(turistaEntity.getTelefono()).ifPresent(usuario::setTelefono);
            Optional.ofNullable(turistaEntity.getFoto()).ifPresent(usuario::setFoto);
            return turistaRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no existe"));
    }

    @Override
    public void deleteById(Long id) {
        turistaRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return turistaRepository.existsById(id);
    }
}
