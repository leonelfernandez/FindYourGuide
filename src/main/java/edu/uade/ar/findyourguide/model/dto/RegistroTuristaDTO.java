package edu.uade.ar.findyourguide.model.dto;

import edu.uade.ar.findyourguide.model.enums.TipoRegistroEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistroTuristaDTO {

    private TipoRegistroEnum tipoRegistro;
    private TuristaDTO turista;

}
