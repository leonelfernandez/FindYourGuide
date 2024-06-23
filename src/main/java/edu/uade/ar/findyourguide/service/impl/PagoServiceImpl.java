package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.exceptions.PagosYaRealizadosError;
import edu.uade.ar.findyourguide.model.adapters.IPagoAdapter;
import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.repository.PagoRepository;
import edu.uade.ar.findyourguide.repository.ReservaRepository;
import edu.uade.ar.findyourguide.service.IPagoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class PagoServiceImpl implements IPagoService {

    private final ReservaRepository reservaRepository;
    private PagoRepository pagoRepository;
    private IPagoAdapter pagoAdapter;

    public PagoServiceImpl(PagoRepository pagoRepository, IPagoAdapter pagoAdapter, ReservaRepository reservaRepository) {
        this.pagoRepository = pagoRepository;
        this.pagoAdapter = pagoAdapter;
        this.reservaRepository = reservaRepository;
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
    public PagoEntity save(PagoEntity pago) throws PagosYaRealizadosError {
        if (this.getPagosByReservaId(pago.getReserva().getId()).size() == 2)
            throw new PagosYaRealizadosError("Los pagos ya fueron realizados");

        return pagoRepository.save(pago);
    }

    private List<PagoEntity> getPagosByReservaId(Long reservaId) {
        return StreamSupport.stream(pagoRepository.findPagoByReservaId(reservaId).spliterator(), false)
                .toList();
    }

    @Override
    public PagoEntity partialUpdate(Long pagoId, PagoEntity pagoEntity) {
        pagoEntity.setId(pagoId);
        return pagoRepository.findById(pagoId).map(pago -> {
            Optional.ofNullable(pagoEntity.getReserva()).ifPresent(pago::setReserva);
            Optional.ofNullable(pagoEntity.getFecha()).ifPresent(pago::setFecha);
            Optional.ofNullable(pagoEntity.getMontoTotal()).ifPresent(pago::setMontoTotal);
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
