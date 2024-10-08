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
public class PagoDTO {

    private Long id;

    private Float montoTotal;

    private Date fecha;

    private String referencia;

    private Long reservaId;

    private Long facturaId;


}
