package edu.uade.ar.findyourguide.repository;


import edu.uade.ar.findyourguide.model.entity.TrofeoEntity;
import edu.uade.ar.findyourguide.model.enums.TipoTrofeoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrofeoRepository extends JpaRepository<TrofeoEntity, Long> {

    Optional<TrofeoEntity> findByTipo(TipoTrofeoEnum tipoTrofeoEnum);
}
