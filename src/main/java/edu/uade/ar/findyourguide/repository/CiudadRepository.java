package edu.uade.ar.findyourguide.repository;

import edu.uade.ar.findyourguide.model.entity.CiudadEntity;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CiudadRepository extends JpaRepository<CiudadEntity, Long> {

    @Query("SELECT c FROM CiudadEntity c WHERE c.id in (:id)")
    public Iterable<CiudadEntity> getAllCiudadesById(List<Long> id);

}
