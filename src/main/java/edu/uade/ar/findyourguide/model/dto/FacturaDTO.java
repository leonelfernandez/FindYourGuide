package edu.uade.ar.findyourguide.model.dto;


import edu.uade.ar.findyourguide.model.enums.TipoPagoEnum;
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
    private TipoPagoEnum detalle;
    private Date fecha;


}
