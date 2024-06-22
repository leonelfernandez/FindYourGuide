package edu.uade.ar.findyourguide.repository;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuiaRepository extends JpaRepository<GuiaEntity, Long> {


    @Query("SELECT g FROM TarifaEntity t JOIN t.guia g JOIN t.ciudad c JOIN c.pais p WHERE p.id = :idPais")
    public Iterable<GuiaEntity> findByPais(Long idPais);

    @Query("SELECT g FROM TarifaEntity t JOIN t.guia g JOIN t.ciudad c WHERE c.id = :idCiudad")
    public Iterable<GuiaEntity> findByCiudad(Long idCiudad);

    @Query("SELECT g FROM GuiaEntity g WHERE g.nombre = :nombre")
    public Iterable<GuiaEntity> findByNombre(String nombre);

    @Query("SELECT g FROM GuiaEntity g WHERE g.apellido = :apellido")
    public Iterable<GuiaEntity> findByApellido(String apellido);


    @Query("SELECT g FROM GuiaEntity g WHERE g.puntajePromedio = :puntuacion")
    public Iterable<GuiaEntity> findByPuntuacion(Float puntuacion);


    @Query("SELECT g FROM GuiaEntity g JOIN g.idiomas i WHERE i.id in (:idioma)")
    public Iterable<GuiaEntity> findByIdioma(List<Long> idioma);


    @Query("SELECT g FROM GuiaEntity g JOIN g.serviciosOfrecidos s WHERE s.id in (:idServicios)")
    public Iterable<GuiaEntity> findByServicios(List<Long> idServicios);

    @Query("SELECT g FROM GuiaEntity g JOIN g.trofeos t WHERE t.id = :idTrofeo")
    public Iterable<GuiaEntity> getGuiasConTrofeo(@Param("idTrofeo") Long idTrofeo);

}
