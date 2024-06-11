package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.repository.PagoRepository;
import edu.uade.ar.findyourguide.service.IPagoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements IPagoService {
    private PagoRepository pagoRepository;

    public PagoServiceImpl(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public List<PagoEntity> findAll() {
        return new ArrayList<>(pagoRepository.findAll());
    }

    @Override
    public Optional<PagoEntity> findById(Long id) {
        return pagoRepository.findById(id);
    }


    @Override
    public PagoEntity save(PagoEntity pago, ReservaEntity reserva) {
        reserva.pagarAnticipo(pago);
        return pagoRepository.save(pago);
    }

    @Override
    public PagoEntity partialUpdate(Long pagoId, PagoEntity pagoEntity) {
        pagoEntity.setId(pagoId);

        return pagoRepository.findById(pagoId).map(pago -> {
            Optional.ofNullable(pagoEntity.getReserva()).ifPresent(pago::setReserva);
            Optional.ofNullable(pagoEntity.getFechaEmision()).ifPresent(pago::setFechaEmision);
            Optional.ofNullable(pagoEntity.getMontoTotal()).ifPresent(pago::setMontoTotal);
            Optional.ofNullable(pagoEntity.getPorcentajeAnticipo()).ifPresent(pago::setPorcentajeAnticipo);
            return pagoRepository.save(pago);
        }).orElseThrow(() -> new RuntimeException("Pago no existe"));
    }




    @Override
    public void deleteById(Long id) {
        pagoRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return pagoRepository.existsById(id);
    }
}
