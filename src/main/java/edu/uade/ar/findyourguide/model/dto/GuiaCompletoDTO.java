package edu.uade.ar.findyourguide.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuiaCompletoDTO {

    private GuiaDTO guia;
    private List<ReseniaDTO> resenias;
    private List<ViajesRealizadosDTO> reservas;
}
