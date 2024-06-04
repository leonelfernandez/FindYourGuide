package edu.uade.ar.findyourguide.repository;

import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TuristaRepository extends JpaRepository<TuristaEntity, Long> {
}
