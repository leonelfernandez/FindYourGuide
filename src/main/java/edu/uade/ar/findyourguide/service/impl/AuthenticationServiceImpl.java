package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.IdiomaEntity;
import edu.uade.ar.findyourguide.model.entity.ServicioEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.repository.GuiaRepository;
import edu.uade.ar.findyourguide.repository.IdiomaRepository;
import edu.uade.ar.findyourguide.repository.ServicioRepository;
import edu.uade.ar.findyourguide.repository.TuristaRepository;
import edu.uade.ar.findyourguide.service.IAuthenticationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
    private GuiaRepository guiaRepository;
    private IdiomaRepository idiomaRepository;
    private ServicioRepository servicioRepository;
    private TuristaRepository turistaRepository;

    public AuthenticationServiceImpl(GuiaRepository guiaRepository, IdiomaRepository idiomaRepository, ServicioRepository servicioRepository, TuristaRepository turistaRepository) {
        this.guiaRepository = guiaRepository;
        this.idiomaRepository = idiomaRepository;
        this.servicioRepository = servicioRepository;
        this.turistaRepository = turistaRepository;
    }

    @Override
    public GuiaEntity registrarGuia(GuiaEntity guiaEntity) {
        List<IdiomaEntity> idiomasRepo = idiomaRepository.findAll();
        List<ServicioEntity> serviciosRepo = servicioRepository.findAll();

        List<IdiomaEntity> filteredIdiomasRepo = idiomasRepo.stream()
                .filter(guiaEntity.getIdiomas()::contains)
                .toList();
        List<ServicioEntity> filteredServiciosRepo = serviciosRepo.stream()
                .filter(guiaEntity.getServiciosOfrecidos()::contains)
                .toList();

        guiaEntity.setIdiomas(filteredIdiomasRepo);
        guiaEntity.setServiciosOfrecidos(filteredServiciosRepo);

        return guiaRepository.save(guiaEntity);
    }

    @Override
    public TuristaEntity registrarTurista(TuristaEntity turista) {
        return turistaRepository.save(turista);
    }

}
