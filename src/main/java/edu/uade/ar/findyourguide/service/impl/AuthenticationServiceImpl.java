package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.entity.*;
import edu.uade.ar.findyourguide.model.enums.TipoRegistroEnum;
import edu.uade.ar.findyourguide.model.strategy.IRegisterStrategy;
import edu.uade.ar.findyourguide.repository.GuiaRepository;
import edu.uade.ar.findyourguide.repository.IdiomaRepository;
import edu.uade.ar.findyourguide.repository.ServicioRepository;
import edu.uade.ar.findyourguide.repository.TuristaRepository;
import edu.uade.ar.findyourguide.service.IAuthenticationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
    private GuiaRepository guiaRepository;
    private IdiomaRepository idiomaRepository;
    private ServicioRepository servicioRepository;
    private TuristaRepository turistaRepository;
    private final Map<TipoRegistroEnum, IRegisterStrategy> registroPorTipo;


    public AuthenticationServiceImpl(GuiaRepository guiaRepository, IdiomaRepository idiomaRepository, ServicioRepository servicioRepository, TuristaRepository turistaRepository, Map<TipoRegistroEnum, IRegisterStrategy> registroPorTipo) {
        this.guiaRepository = guiaRepository;
        this.idiomaRepository = idiomaRepository;
        this.servicioRepository = servicioRepository;
        this.turistaRepository = turistaRepository;
        this.registroPorTipo = registroPorTipo;
    }

    @Override
    public GuiaEntity registrarGuia(GuiaEntity guiaEntity, TipoRegistroEnum tipoDeRegistro) {
        IRegisterStrategy registerStrategy = this.registroPorTipo.get(tipoDeRegistro);
        registerStrategy.registrarse(guiaEntity);

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
    public TuristaEntity registrarTurista(TuristaEntity turista, TipoRegistroEnum tipoDeRegistro) {
        IRegisterStrategy registerStrategy = this.registroPorTipo.get(tipoDeRegistro);
        registerStrategy.registrarse(turista);
        return turistaRepository.save(turista);
    }

    @Override
    public UsuarioEntity login(String email, String password) {
        Optional<GuiaEntity> guiaOpt = guiaRepository.findByEmailAndPassword(email, password);
        if(guiaOpt.isPresent()) {
            return guiaOpt.get();
        }
        return turistaRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

}
