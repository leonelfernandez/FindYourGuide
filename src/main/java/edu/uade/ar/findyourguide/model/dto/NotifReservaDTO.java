package edu.uade.ar.findyourguide.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotifReservaDTO {

    private String mensaje;
    private ReservaDTO reserva;

}
