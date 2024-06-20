package edu.uade.ar.findyourguide.repository;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<PagoEntity, Long> {

    @Query("SELECT p FROM PagoEntity p WHERE p.reserva.id = :id")
    public Iterable<PagoEntity> findPagoByReservaId(Long id);
}
