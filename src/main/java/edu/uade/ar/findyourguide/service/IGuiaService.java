package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;

import java.util.List;
import java.util.Optional;

public interface IGuiaService {
    public List<GuiaEntity> findAll();

    public Optional<GuiaEntity> findById(Long id);

    public GuiaEntity save(GuiaEntity usuario);

    public GuiaEntity partialUpdate(Long clienteId, GuiaEntity usuario);

    public void deleteById(Long id);

    public boolean isExists(Long id);


    public List<GuiaEntity> findByPais(Long id);

    public List<GuiaEntity> findByCiudad(Long id);

    public List<GuiaEntity> findByNombre(String nombre);

    public List<GuiaEntity> findByApellido(String apellido);

    public List<GuiaEntity> findByPuntuacion(Float puntuacion);

    public List<GuiaEntity> findByIdiomas(List<Long> idioma);

    public List<GuiaEntity> findByServicios(List<Long> idServicios);
}
