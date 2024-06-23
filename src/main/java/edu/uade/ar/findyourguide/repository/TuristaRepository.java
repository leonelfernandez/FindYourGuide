package edu.uade.ar.findyourguide.repository;

import edu.uade.ar.findyourguide.model.entity.CiudadEntity;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
import edu.uade.ar.findyourguide.model.enums.TipoRegistroEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TuristaRepository extends JpaRepository<TuristaEntity, Long> {

    @Query("SELECT t FROM TuristaEntity t JOIN t.trofeos tr WHERE tr.id = :idTrofeo")
    public Iterable<TuristaEntity> getTuristasConTrofeo(@Param("idTrofeo") Long idTrofeo);

    @Query("SELECT t FROM TuristaEntity t WHERE t.email = :email AND t.password = :password")
    public Optional<TuristaEntity> findByEmailAndPassword(String email, String password);

    @Query("SELECT r.ciudad FROM TuristaEntity t JOIN ReservaEntity r ON r.turista.id = :id AND r.estado = :estado")
    public Iterable<CiudadEntity> findViajesRealizados(Long id, ReservaStateEnum estado);
}
