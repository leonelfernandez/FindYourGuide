package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.ViajeEntity;

import java.util.List;
import java.util.Optional;

public interface IViajeService {

    public List<ViajeEntity> findAll();

    public Optional<ViajeEntity> findById(Long id);

    public ViajeEntity save(ViajeEntity viaje);

    public ViajeEntity partialUpdate(Long viajeId, ViajeEntity viaje);

    public void deleteById(Long id);

    public boolean isExists(Long id);


}
