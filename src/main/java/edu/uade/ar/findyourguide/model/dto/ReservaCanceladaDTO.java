package edu.uade.ar.findyourguide.model.dto;

import edu.uade.ar.findyourguide.util.ReservaMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservaCanceladaDTO {

    private String mensaje;
    private ReservaDTO reserva;

}
