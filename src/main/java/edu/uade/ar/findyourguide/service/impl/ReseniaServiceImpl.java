package edu.uade.ar.findyourguide.service.impl;


import edu.uade.ar.findyourguide.model.entity.ReseniaEntity;
import edu.uade.ar.findyourguide.model.observer.impl.TrofeoGuiaObserver;
import edu.uade.ar.findyourguide.model.observer.impl.TrofeoTuristaObserver;
import edu.uade.ar.findyourguide.repository.ReseniaRepository;
import edu.uade.ar.findyourguide.service.IReseniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ReseniaServiceImpl implements IReseniaService {

    private ReseniaRepository reseniaRepository;

    private TrofeoGuiaObserver trofeoGuiaObserver;

    private TrofeoTuristaObserver trofeoTuristaObserver;


    public ReseniaServiceImpl(ReseniaRepository reseniaRepository, TrofeoGuiaObserver trofeoGuiaObserver, TrofeoTuristaObserver trofeoTuristaObserver, ReservaServiceImpl reservaService) {
        this.reseniaRepository = reseniaRepository;
        this.trofeoGuiaObserver = trofeoGuiaObserver;
        this.trofeoTuristaObserver = trofeoTuristaObserver;
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
        resenia.attach(trofeoGuiaObserver);
        resenia.attach(trofeoTuristaObserver);
        resenia.notificar(resenia);
        return reseniaRepository.save(resenia);
    }

    @Override
    public ReseniaEntity partialUpdate(Long reseniaId, ReseniaEntity reseniaEntity) {
        reseniaEntity.setId(reseniaId);

        return reseniaRepository.findById(reseniaId).map(resenia -> {
            Optional.ofNullable(reseniaEntity.getComentario()).ifPresent(resenia::setComentario);
            Optional.ofNullable(reseniaEntity.getGuia()).ifPresent(resenia::setGuia);
            Optional.ofNullable(reseniaEntity.getTurista()).ifPresent(resenia::setTurista);
            Optional.ofNullable(reseniaEntity.getPuntuacion()).ifPresent(resenia::setPuntuacion);

            return reseniaRepository.save(resenia);
        }).orElseThrow(() -> new RuntimeException("Resenia no existe"));
    }

    @Override
    public void deleteById(Long id) {
        reseniaRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return reseniaRepository.existsById(id);
    }

    @Override
    public Iterable<ReseniaEntity> getReseniasByGuia(Long id) {
        return reseniaRepository.getReseniasByGuia(id);
    }

    //Cantidad de resenias hechas a guia por parte del turista. Si es la misma cantidad no puede generar una nueva, si es menor si puede.
    @Override
    public Boolean validarTuristaHizoElViaje(Long idTurista, Long idGuia) {
        List<ReseniaEntity> reseniasTurista = StreamSupport.stream(reseniaRepository.getReseniasByTurista(idTurista).spliterator(), false).toList();
        return StreamSupport.stream(reseniaRepository.validarTuristaHizoElViaje(idTurista, idGuia).spliterator(), false).toList().size() ==
                reseniasTurista.stream().filter(r -> r.getGuia().getId().equals(idGuia)).toList().size();
    }
}
