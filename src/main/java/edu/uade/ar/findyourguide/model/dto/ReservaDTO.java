package edu.uade.ar.findyourguide.model.dto;


import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaDTO {
    private Long id;
    private Long guiaId;
    private Long turistaId;
    private List<ServicioDTO> serviciosContratados;
    private Date fechaInicio;
    private Date fechaFin;
    private Long ciudadId;
    private ReservaStateEnum estado;

}
