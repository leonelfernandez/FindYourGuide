package edu.uade.ar.findyourguide.model.dto;

import edu.uade.ar.findyourguide.model.entity.CiudadEntity;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaDTO {
    private Long id;
    private GuiaEntity guia;

    private TuristaEntity turista;
    private Date fechaInicio;
    private Date fechaFin;

    private Float precioTotal;

    private String estado;

    private CiudadEntity ciudad;

}
