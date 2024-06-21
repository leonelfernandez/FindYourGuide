package edu.uade.ar.findyourguide.repository;

import edu.uade.ar.findyourguide.model.entity.ReseniaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReseniaRepository extends JpaRepository<ReseniaEntity, Long> {
}
