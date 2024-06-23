package edu.uade.ar.findyourguide.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MontoAPagarReservaDTO {

    private Long ciudadDestinoId;
    private Float montoAPagarAnticipo;
    private Float montoAPagarRestante;
    private Float montoAPagarComisionPlataforma;
    private Float montoAPagarTotal;



}
