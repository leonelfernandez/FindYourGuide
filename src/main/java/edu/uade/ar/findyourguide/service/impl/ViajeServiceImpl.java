package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.entity.ViajeEntity;
import edu.uade.ar.findyourguide.repository.ViajeRepository;
import edu.uade.ar.findyourguide.service.IViajeService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViajeServiceImpl implements IViajeService {
    private ViajeRepository viajeRepository;

    public ViajeServiceImpl(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }

    @Override
    public ViajeEntity save(ViajeEntity viajeEntity) {
        return viajeRepository.save(viajeEntity);
    }

    @Override
    public List<ViajeEntity> findAll() {
        return new ArrayList<>(viajeRepository.findAll());
    }

    @Override
    public Optional<ViajeEntity> findById(Long id) {
        return viajeRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return viajeRepository.existsById(id);
    }

    @Override
    public ViajeEntity partialUpdate(Long id, ViajeEntity viajeEntity) {
        viajeEntity.setId(id);

        return viajeRepository.findById(id).map(viaje -> {
            Optional.ofNullable(viajeEntity.getReserva()).ifPresent(viaje::setReserva);
            Optional.ofNullable(viajeEntity.getPrecio()).ifPresent(viaje::setPrecio);
            Optional.ofNullable(viajeEntity.getDestino()).ifPresent(viaje::setDestino);
            Optional.ofNullable(viajeEntity.getFechaFin()).ifPresent(viaje::setFechaFin);
            Optional.ofNullable(viajeEntity.getFechaInicio()).ifPresent(viaje::setFechaInicio);
            return viajeRepository.save(viaje);
        }).orElseThrow(() -> new RuntimeException("Viaje no existe"));
    }

    @Override
    public void deleteById(Long id) {
        viajeRepository.deleteById(id);
    }



}
