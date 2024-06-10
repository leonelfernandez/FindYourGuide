package edu.uade.ar.findyourguide.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagoDTO {

    private Long id;
    private Float montoTotal;
    private Date fechaEmision;

    private Float porcentajeAnticipo;

    private Long reservaId;


}
