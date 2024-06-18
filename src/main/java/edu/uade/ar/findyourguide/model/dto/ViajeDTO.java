package edu.uade.ar.findyourguide.model.dto;

import edu.uade.ar.findyourguide.model.entity.CiudadEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViajeDTO {

    private Long id;
    private Long destinoId;

    private Float precio;
    private Date fechaInicio;
    private Date fechaFin;

}
