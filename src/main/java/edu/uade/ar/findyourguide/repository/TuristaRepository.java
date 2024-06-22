package edu.uade.ar.findyourguide.repository;

import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TuristaRepository extends JpaRepository<TuristaEntity, Long> {

    @Query("SELECT t FROM TuristaEntity t JOIN t.trofeos tr WHERE tr.id = :idTrofeo")
    public Iterable<TuristaEntity> getTuristasConTrofeo(@Param("idTrofeo") Long idTrofeo);
}
