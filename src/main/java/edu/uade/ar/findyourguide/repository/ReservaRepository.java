package edu.uade.ar.findyourguide.repository;

import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {


    @Query("SELECT t.precio from TarifaEntity t where t.ciudad.id = :idCiudad and t.guia.id = :idGuia")
    public Float findMontoTotalReserva(Long idCiudad, Long idGuia);


    @Query("SELECT COUNT(*) FROM ReservaEntity r WHERE (:fechaInicio BETWEEN r.fechaInicio AND r.fechaFin OR :fechaFin BETWEEN r.fechaInicio AND r.fechaFin)")
    public Long countOverlapping(Date fechaInicio, Date fechaFin);

    @Query("SELECT COUNT(*) FROM ReservaEntity r WHERE r.estado IN (:estado1, :estado2, :estado3)")
    public Long countFinalized(ReservaStateEnum estado1, ReservaStateEnum estado2, ReservaStateEnum estado3);
}
