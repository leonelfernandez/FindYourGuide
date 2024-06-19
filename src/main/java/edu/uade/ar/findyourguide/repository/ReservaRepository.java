package edu.uade.ar.findyourguide.repository;

import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {


    @Query("SELECT t.precio from TarifaEntity t where t.ciudad.id = :idCiudad and t.guia.id = :idGuia")
    public Float findMontoTotalReserva(Long idCiudad, Long idGuia);


}
