package edu.uade.ar.findyourguide.model.dto;

import edu.uade.ar.findyourguide.model.entity.CiudadEntity;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
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

    private ReservaStateEnum estado;

    private CiudadEntity ciudad;

}
