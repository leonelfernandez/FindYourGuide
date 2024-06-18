package edu.uade.ar.findyourguide.model.dto;


import edu.uade.ar.findyourguide.model.entity.ServicioEntity;
import edu.uade.ar.findyourguide.model.entity.CiudadEntity;
import edu.uade.ar.findyourguide.model.entity.IdiomaEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GuiaDTO extends UsuarioDTO {

    private Float puntajePromedio;
    private String credencial;
    private String fotoVerificacion;
    private List<ServicioDTO> serviciosOfrecidos;
    private List<IdiomaEntity> idiomas;
}
