package edu.uade.ar.findyourguide.repository;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TarifaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends JpaRepository<TarifaEntity, Long> {


    @Query("SELECT t.precio FROM TarifaEntity t WHERE t.guia.id = :id AND t.ciudad.id = :ciudadDestinoId")
    public Float findMontoTotalReserva(Long id, Long ciudadDestinoId);
}
