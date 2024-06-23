package edu.uade.ar.findyourguide.model.observer.impl;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.ReseniaEntity;
import edu.uade.ar.findyourguide.model.entity.TrofeoEntity;
import edu.uade.ar.findyourguide.model.enums.TipoTrofeoEnum;
import edu.uade.ar.findyourguide.model.observer.IObserver;
import edu.uade.ar.findyourguide.repository.GuiaRepository;
import edu.uade.ar.findyourguide.repository.ReseniaRepository;
import edu.uade.ar.findyourguide.repository.TrofeoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Component
public class TrofeoGuiaObserver implements IObserver {

    private final ReseniaRepository reseniaRepository;
    private final TrofeoRepository trofeoRepository;
    private final GuiaRepository guiaRepository;

    public TrofeoGuiaObserver(TrofeoRepository trofeoRepository, GuiaRepository guiaRepository, ReseniaRepository reseniaRepository) {
        this.trofeoRepository = trofeoRepository;
        this.guiaRepository = guiaRepository;
        this.reseniaRepository = reseniaRepository;
    }

    @Override
    public void actualizar(ReseniaEntity resenia) {
        GuiaEntity guia = guiaRepository.findById(resenia.getGuia().getId()).orElseThrow();
        List<ReseniaEntity> resenias = StreamSupport.stream(reseniaRepository.getReseniasForGuia(guia.getId()).spliterator(), false).toList();
        if (resenias.isEmpty())
            guia.setPuntajePromedio(resenia.getPuntuacion());
        else {
            Float sum = resenia.getPuntuacion(); //Aun no esta persistido en la base.
            for (ReseniaEntity r : resenias) {
                sum += r.getPuntuacion();
            }
            Float average = sum / (resenias.size() + 1);
            guia.setPuntajePromedio(average);

            if (average >= 4.5f && resenias.size() + 1 >= 10 && guia.getTrofeos().isEmpty()) {
                Optional<TrofeoEntity> trofeoOpt = trofeoRepository.findByTipo(TipoTrofeoEnum.EXITO);
                trofeoOpt.ifPresent(trofeo -> guia.getTrofeos().add(trofeo));
            } else if (average <= 4.5f) {
                guia.getTrofeos().removeIf(trofeo -> trofeo.getTipo().equals(TipoTrofeoEnum.EXITO));
            }

        }
        guiaRepository.save(guia);
    }

}

