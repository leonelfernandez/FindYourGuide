package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.exceptions.PagoNoRealizadoError;
import edu.uade.ar.findyourguide.exceptions.PagosYaRealizadosError;
import edu.uade.ar.findyourguide.model.entity.FacturaEntity;
import edu.uade.ar.findyourguide.model.entity.PagoEntity;

import java.util.List;
import java.util.Optional;

public interface IFacturaService {

    public List<FacturaEntity> findAll();

    public Optional<FacturaEntity> findById(Long id);

    public FacturaEntity save(FacturaEntity factura);

    public FacturaEntity partialUpdate(Long facturaId, FacturaEntity factura);

    public void deleteById(Long id);

    public boolean isExists(Long id);


}


