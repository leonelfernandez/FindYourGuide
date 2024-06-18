package edu.uade.ar.findyourguide.model.dto;


import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.model.entity.ViajeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaDTO {
    private Long id;
    private Long guiaId;
    private Long turistaId;
    private ViajeDTO viaje;
    private List<Long> servicios;

}
