package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.entity.TarifaEntity;
import edu.uade.ar.findyourguide.repository.TarifaRepository;
import edu.uade.ar.findyourguide.service.ITarifaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarifaServiceImpl implements ITarifaService {

    private TarifaRepository tarifaRepository;


    public TarifaServiceImpl(TarifaRepository tarifaRepository) {
        this.tarifaRepository = tarifaRepository;
    }

    @Override
    public List<TarifaEntity> findAll() {
        return new ArrayList<>(tarifaRepository.findAll());
    }

    @Override
    public Optional<TarifaEntity> findById(Long id) {
        return tarifaRepository.findById(id);
    }

    @Override
    public TarifaEntity save(TarifaEntity tarifa) {
        return tarifaRepository.save(tarifa);
    }

    @Override
    public TarifaEntity partialUpdate(Long tarifaId, TarifaEntity tarifaEntity) {
        tarifaEntity.setId(tarifaId);

        return tarifaRepository.findById(tarifaId).map(tarifa -> {
            Optional.ofNullable(tarifaEntity.getGuia()).ifPresent(tarifa::setGuia);
            Optional.ofNullable(tarifaEntity.getPrecio()).ifPresent(tarifa::setPrecio);
            Optional.ofNullable(tarifaEntity.getCiudad()).ifPresent(tarifa::setCiudad);

            return tarifaRepository.save(tarifa);
        }).orElseThrow(() -> new RuntimeException("Tarifa no existe"));
    }

    @Override
    public void deleteById(Long id) {
        tarifaRepository.deleteById(id);

    }

    @Override
    public boolean isExists(Long id) {
        return tarifaRepository.existsById(id);
    }
}
