package edu.uade.ar.findyourguide.model.dto;

import edu.uade.ar.findyourguide.model.entity.PaisEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CiudadDTO {
    private Long id;
    private String nombre;
    private PaisEntity pais;
}