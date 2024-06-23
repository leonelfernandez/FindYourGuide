package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.entity.TrofeoEntity;
import edu.uade.ar.findyourguide.repository.TrofeoRepository;
import edu.uade.ar.findyourguide.service.ITrofeoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrofeoServiceImpl implements ITrofeoService {

    private TrofeoRepository trofeoRepository;

    public TrofeoServiceImpl(TrofeoRepository trofeoRepository) {
        this.trofeoRepository = trofeoRepository;
    }

    @Override
    public List<TrofeoEntity> findAll() {
        return new ArrayList<>(trofeoRepository.findAll());
    }

    @Override
    public Optional<TrofeoEntity> findById(Long id) {
        return trofeoRepository.findById(id);
    }

    @Override
    public TrofeoEntity save(TrofeoEntity trofeo) {
        return trofeoRepository.save(trofeo);
    }

    @Override
    public TrofeoEntity partialUpdate(Long trofeoId, TrofeoEntity trofeoEntity) {
        trofeoEntity.setId(trofeoId);

        return trofeoRepository.findById(trofeoId).map(trofeo -> {
            Optional.ofNullable(trofeoEntity.getTipo()).ifPresent(trofeo::setTipo);
            Optional.ofNullable(trofeoEntity.getUsuarios()).ifPresent(trofeo::setUsuarios);
            return trofeoRepository.save(trofeo);
        }).orElseThrow(() -> new RuntimeException("Trofeo no existe"));
    }

    @Override
    public void deleteById(Long id) {
        trofeoRepository.deleteById(id);

    }

    @Override
    public boolean isExists(Long id) {
        return trofeoRepository.existsById(id);
    }
}
