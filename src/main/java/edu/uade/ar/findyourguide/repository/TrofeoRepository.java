package edu.uade.ar.findyourguide.repository;

import edu.uade.ar.findyourguide.model.entity.TrofeoEntity;
import edu.uade.ar.findyourguide.model.enums.TrofeoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrofeoRepository extends JpaRepository<TrofeoEntity, Long> {

    @Query("SELECT t from TrofeoEntity t where t.tipo = :trofeoTipo")
    TrofeoEntity findByTipo(TrofeoEnum trofeoTipo);
}
