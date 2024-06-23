package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.entity.FacturaEntity;
import edu.uade.ar.findyourguide.repository.FacturaRepository;
import edu.uade.ar.findyourguide.service.IFacturaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements IFacturaService {

    private FacturaRepository facturaRepository;

    public FacturaServiceImpl(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public List<FacturaEntity> findAll() {
        return new ArrayList<>(facturaRepository.findAll());
    }

    @Override
    public Optional<FacturaEntity> findById(Long id) {
        return facturaRepository.findById(id);
    }

    @Override
    public FacturaEntity save(FacturaEntity factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public FacturaEntity partialUpdate(Long facturaId, FacturaEntity facturaEntity) {
        facturaEntity.setId(facturaId);

        return facturaRepository.findById(facturaId).map(factura -> {
            Optional.ofNullable(facturaEntity.getDetalle()).ifPresent(factura::setDetalle);
            Optional.ofNullable(facturaEntity.getMonto()).ifPresent(factura::setMonto);
            return facturaRepository.save(factura);
        }).orElseThrow(() -> new RuntimeException("Factura no existe"));
    }

    @Override
    public void deleteById(Long id) {
        facturaRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return facturaRepository.existsById(id);
    }
}
