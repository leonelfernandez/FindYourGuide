package edu.uade.ar.findyourguide.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotifGuiaCreadoDTO {

    private String mensaje;
    private GuiaDTO guia;


}
