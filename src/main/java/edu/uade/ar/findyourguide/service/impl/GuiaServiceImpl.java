package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.IdiomaEntity;
import edu.uade.ar.findyourguide.model.entity.ServicioEntity;
import edu.uade.ar.findyourguide.repository.GuiaRepository;
import edu.uade.ar.findyourguide.repository.IdiomaRepository;
import edu.uade.ar.findyourguide.repository.ServicioRepository;
import edu.uade.ar.findyourguide.service.IGuiaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GuiaServiceImpl implements IGuiaService {

    private GuiaRepository guiaRepository;
    private IdiomaRepository idiomaRepository;

    private ServicioRepository servicioRepository;

    public GuiaServiceImpl(GuiaRepository guiaRepository, IdiomaRepository idiomaRepository, ServicioRepository servicioRepository) {
        this.guiaRepository = guiaRepository;
        this.idiomaRepository = idiomaRepository;
        this.servicioRepository = servicioRepository;
    }

    @Override
    public GuiaEntity save(GuiaEntity guiaEntity) {
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
    public List<GuiaEntity> findAll() {
        return new ArrayList<>(guiaRepository.findAll());
    }

    @Override
    public Optional<GuiaEntity> findById(Long id) {
        return guiaRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return guiaRepository.existsById(id);
    }

    @Override
    public List<GuiaEntity> findByPais(Long idPais) {
        return StreamSupport.stream(guiaRepository.findByPais(idPais).spliterator(), false)
                .toList();
    }

    @Override
    public List<GuiaEntity> findByCiudad(Long id) {
        return StreamSupport.stream(guiaRepository.findByCiudad(id).spliterator(), false)
                .toList();
    }

    @Override
    public List<GuiaEntity> findByNombre(String nombre) {
        return StreamSupport.stream(guiaRepository.findByNombre(nombre).spliterator(), false)
                .toList();
    }

    @Override
    public List<GuiaEntity> findByApellido(String apellido) {
        return StreamSupport.stream(guiaRepository.findByApellido(apellido).spliterator(), false)
                .toList();
    }

    @Override
    public List<GuiaEntity> findByPuntuacion(Float puntuacion) {
        return StreamSupport.stream(guiaRepository.findByPuntuacion(puntuacion).spliterator(), false)
                .toList();
    }

    @Override
    public List<GuiaEntity> findByIdiomas(List<Long> idiomas) {
        return StreamSupport.stream(guiaRepository.findByIdioma(idiomas).spliterator(), false)
                .toList();
    }

    @Override
    public List<GuiaEntity> findByServicios(List<Long> idServicios) {
        return StreamSupport.stream(guiaRepository.findByServicios(idServicios).spliterator(), false)
                .toList();
    }

    @Override
    public GuiaEntity partialUpdate(Long id, GuiaEntity guiaEntity) {
        guiaEntity.setId(id);

        return guiaRepository.findById(id).map(usuario -> {
            Optional.ofNullable(guiaEntity.getNombre()).ifPresent(usuario::setNombre);
            Optional.ofNullable(guiaEntity.getApellido()).ifPresent(usuario::setApellido);
            Optional.ofNullable(guiaEntity.getDni()).ifPresent(usuario::setDni);
            Optional.ofNullable(guiaEntity.getSexo()).ifPresent(usuario::setSexo);
            Optional.ofNullable(guiaEntity.getFoto()).ifPresent(usuario::setFoto);
            Optional.ofNullable(guiaEntity.getEmail()).ifPresent(usuario::setEmail);
            Optional.ofNullable(guiaEntity.getPassword()).ifPresent(usuario::setPassword);
            Optional.ofNullable(guiaEntity.getCredencial()).ifPresent(usuario::setCredencial);
            Optional.ofNullable(guiaEntity.getFotoVerificacion()).ifPresent(usuario::setFotoVerificacion);
            Optional.ofNullable(guiaEntity.getIdiomas()).ifPresent(usuario::setIdiomas);
            Optional.ofNullable(guiaEntity.getTelefono()).ifPresent(usuario::setTelefono);
            Optional.ofNullable(guiaEntity.getPuntajePromedio()).ifPresent(usuario::setPuntajePromedio);
            return guiaRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no existe"));
    }

    @Override
    public void deleteById(Long id) {
        guiaRepository.deleteById(id);
    }



}
