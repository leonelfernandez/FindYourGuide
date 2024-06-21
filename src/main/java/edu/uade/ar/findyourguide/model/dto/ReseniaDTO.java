package edu.uade.ar.findyourguide.model.dto;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReseniaDTO {
    private Long id;
    private Integer puntuacion;
    private String comentario;
    private Long guia_id;
    private Long turista_id;
}
