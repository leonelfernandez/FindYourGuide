package edu.uade.ar.findyourguide.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaSinEstadoDTO {
    private Long id;
    private Long guiaId;
    private Long turistaId;
    private Long servicioId;
    private Date fechaInicio;
    private Date fechaFin;
    private Long ciudadId;
}
