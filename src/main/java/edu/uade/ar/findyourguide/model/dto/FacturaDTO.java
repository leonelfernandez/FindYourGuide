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
public class FacturaDTO {

    private Float montoTotalAPagar;
    private String detalle;
    private Date fecha;


}
