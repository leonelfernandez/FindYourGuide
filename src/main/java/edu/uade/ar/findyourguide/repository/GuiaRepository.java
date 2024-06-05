package edu.uade.ar.findyourguide.repository;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GuiaRepository extends JpaRepository<GuiaEntity, Long> {


    @Query("SELECT g FROM GuiaEntity g JOIN g.ciudades c JOIN c.pais p WHERE p.id = :idPais")
    Iterable<GuiaEntity> findByPais(Long idPais);

    @Query("SELECT g FROM GuiaEntity g JOIN g.ciudades c WHERE c.id = :idCiudad")
    Iterable<GuiaEntity> findByCiudad(Long idCiudad);

    @Query("SELECT g FROM GuiaEntity g WHERE g.nombre = :nombre")
    Iterable<GuiaEntity> findByNombre(String nombre);

    @Query("SELECT g FROM GuiaEntity g WHERE g.apellido = :apellido")
    Iterable<GuiaEntity> findByApellido(String apellido);

    Iterable<GuiaEntity> findByIdioma();

    Iterable<GuiaEntity> findByTipoDeServicios();

    @Query("SELECT g FROM GuiaEntity g WHERE g.puntajePromedio = :puntuacion")
    Iterable<GuiaEntity> findByPuntuacion(Float puntuacion);


}
