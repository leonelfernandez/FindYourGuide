package edu.uade.ar.findyourguide.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "facturas")
public class FacturaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "factura_id_seq")
    @SequenceGenerator(name = "factura_id_seq", sequenceName = "factura_id_seq",  allocationSize=1)
    private Long id;

    private String detalle;

    private Float monto;

    private Date fecha;


}
