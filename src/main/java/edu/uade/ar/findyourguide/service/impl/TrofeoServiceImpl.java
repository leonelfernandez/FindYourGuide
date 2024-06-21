package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TrofeoEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.model.entity.ReseniaEntity;
import edu.uade.ar.findyourguide.model.enums.TrofeoEnum;
import edu.uade.ar.findyourguide.model.observer.IObserver;
import edu.uade.ar.findyourguide.repository.GuiaRepository;
import edu.uade.ar.findyourguide.repository.TrofeoRepository;
import edu.uade.ar.findyourguide.repository.TuristaRepository;
import edu.uade.ar.findyourguide.repository.UsuarioRepository;
import edu.uade.ar.findyourguide.service.ITrofeoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TrofeoServiceImpl implements ITrofeoService, IObserver {
    private TrofeoRepository trofeoRepository;
    private GuiaRepository guiaRepository;
    private TuristaRepository turistaRepository;

    public TrofeoServiceImpl(TrofeoRepository trofeoRepository, GuiaRepository guiaRepository, TuristaRepository turistaRepository) {
        this.trofeoRepository = trofeoRepository;
        this.turistaRepository = turistaRepository;
        this.guiaRepository = guiaRepository;
    }

    @Override
    public TrofeoEntity save(TrofeoEntity trofeoEntity) {
        return trofeoRepository.save(trofeoEntity);
    }

    @Override
    public TrofeoEntity partialUpdate(Long reservaId, TrofeoEntity reserva) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }


    @Override
    public List<TrofeoEntity> findAll() {
        return new ArrayList<>(trofeoRepository.findAll());
    }

    @Override
    public Optional<TrofeoEntity> findById(Long id) {
        return trofeoRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return trofeoRepository.existsById(id);
    }

    @Override
    public void actualizar(ReseniaEntity resenia){
        evaluarYAsignarTrofeoAlExito(resenia.getGuia());
        evaluarYAsignarTrofeoALaResenia(resenia.getTurista());
    }

    @Override
    public void evaluarYAsignarTrofeoAlExito(GuiaEntity guia) {
        Integer totalResenias = guia.getResenias().size();
        Float puntuacionPromedio = guia.getPuntajePromedio();

        if (totalResenias >= 10 && puntuacionPromedio >= 4.5) {
            TrofeoEntity trofeo = trofeoRepository.findByTipo(TrofeoEnum.EXITO);
            if (trofeo != null && !guia.getTrofeos().contains(trofeo)) {
                guia.getTrofeos().add(trofeo);
                guiaRepository.save(guia);
            }
        }
    }

    @Override
    public void evaluarYAsignarTrofeoALaResenia(TuristaEntity turista) {
        int totalResenias = turista.getResenias().size();

        if (totalResenias > 10) {
            TrofeoEntity trofeo = trofeoRepository.findByTipo(TrofeoEnum.RESENIA);
            if (trofeo != null && !turista.getTrofeos().contains(trofeo)) {
                turista.getTrofeos().add(trofeo);
                turistaRepository.save(turista);
            }
        }
    }
}
