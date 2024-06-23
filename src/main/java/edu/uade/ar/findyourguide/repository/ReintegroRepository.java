package edu.uade.ar.findyourguide.repository;

import edu.uade.ar.findyourguide.model.entity.ReintegroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReintegroRepository extends JpaRepository<ReintegroEntity, Long> {
}
