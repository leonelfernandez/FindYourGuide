package edu.uade.ar.findyourguide.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdiomasGuiaDTO {

    private List<Long> idIdiomas;


}
