package edu.uade.ar.findyourguide.repository;

import edu.uade.ar.findyourguide.model.entity.ReseniaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReseniaRepository extends JpaRepository<ReseniaEntity, Long> {

    @Query("SELECT r FROM ReseniaEntity r WHERE r.guia.id = :id")
    public Iterable<ReseniaEntity> getReseniasForGuia(Long id);

    @Query("SELECT r FROM ReseniaEntity r WHERE r.turista.id = :id")
    Iterable<ReseniaEntity> getReseniasByTurista(Long id);

    @Query("SELECT COUNT(DISTINCT r.guia.id) FROM ReseniaEntity r WHERE r.turista.id = :id")
    public Integer getCantidadReseniasADiferentesGuias(Long id);

}
