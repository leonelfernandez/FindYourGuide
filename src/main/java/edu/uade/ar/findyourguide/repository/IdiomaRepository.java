package edu.uade.ar.findyourguide.repository;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.IdiomaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdiomaRepository extends JpaRepository<IdiomaEntity, Long> {
}
