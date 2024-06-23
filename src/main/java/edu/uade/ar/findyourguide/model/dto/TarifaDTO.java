package edu.uade.ar.findyourguide.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class TarifaDTO {

    private Long id;
    private Long guiaId;
    private Float precio;
    private Long ciudadId;



}
