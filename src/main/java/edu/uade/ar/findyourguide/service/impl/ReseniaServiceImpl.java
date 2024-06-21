package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.entity.ReseniaEntity;
import edu.uade.ar.findyourguide.repository.ReseniaRepository;
import edu.uade.ar.findyourguide.service.IReseniaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ReseniaServiceImpl implements IReseniaService {
    private ReseniaRepository reseniaRepository;

    public ReseniaServiceImpl(ReseniaRepository reseniaRepository) {
        this.reseniaRepository = reseniaRepository;
    }
    @Override
    public List<ReseniaEntity> findAll() {
        return new ArrayList<>(reseniaRepository.findAll());
    }

    @Override
    public Optional<ReseniaEntity> findById(Long id) {
        return reseniaRepository.findById(id);
    }

    @Override
    public ReseniaEntity save(ReseniaEntity resenia) {
        resenia.notificar(resenia);
        return reseniaRepository.save(resenia);
    }

    @Override
    public ReseniaEntity partialUpdate(Long reseniaId, ReseniaEntity reseniaEntity) {
        reseniaEntity.setId(reseniaId);

        return reseniaRepository.findById(reseniaId).map(resenia -> {
            Optional.ofNullable(reseniaEntity.getPuntuacion()).ifPresent(resenia::setPuntuacion);
            Optional.ofNullable(reseniaEntity.getComentario()).ifPresent(resenia::setComentario);
            Optional.ofNullable(reseniaEntity.getGuia()).ifPresent(resenia::setGuia);
            Optional.ofNullable(reseniaEntity.getTurista()).ifPresent(resenia::setTurista);
            return reseniaRepository.save(resenia);
        }).orElseThrow(() -> new RuntimeException("Reseña no existe"));
    }

    @Override
    public void deleteById(Long id) {
        reseniaRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return reseniaRepository.existsById(id);
    }
}
