package edu.uade.ar.findyourguide.model.observer.impl;

import edu.uade.ar.findyourguide.model.entity.ReseniaEntity;
import edu.uade.ar.findyourguide.model.entity.TrofeoEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.model.enums.TipoTrofeoEnum;
import edu.uade.ar.findyourguide.model.observer.IObserver;
import edu.uade.ar.findyourguide.repository.ReseniaRepository;
import edu.uade.ar.findyourguide.repository.TrofeoRepository;
import edu.uade.ar.findyourguide.repository.TuristaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Component
public class TrofeoTuristaObserver implements IObserver {

    private final TuristaRepository turistaRepository;
    private TrofeoRepository trofeoRepository;
    private ReseniaRepository reseniaRepository;

    public TrofeoTuristaObserver(TrofeoRepository trofeoRepository, ReseniaRepository reseniaRepository, TuristaRepository turistaRepository) {
        this.trofeoRepository = trofeoRepository;
        this.reseniaRepository = reseniaRepository;
        this.turistaRepository = turistaRepository;
    }

    @Override
    public void actualizar(ReseniaEntity resenia) {
        TuristaEntity turista = turistaRepository.findById(resenia.getTurista().getId()).orElseThrow();
        Integer cantReseniasDif = reseniaRepository.getCantidadReseniasADiferentesGuias(turista.getId());

        if (cantReseniasDif + 1 > 10 && turista.getTrofeos().isEmpty()) { //En este momento aun no fue persistida la ultima resenia
            Optional<TrofeoEntity> trofeoOpt = trofeoRepository.findByTipo(TipoTrofeoEnum.RESENIA);
            trofeoOpt.ifPresent(trofeo -> turista.getTrofeos().add(trofeo));
        }

        turistaRepository.save(turista);
    }


}
