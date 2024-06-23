package edu.uade.ar.findyourguide.model.entity;


import edu.uade.ar.findyourguide.model.enums.TipoPagoEnum;
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

    @Enumerated(EnumType.STRING)
    private TipoPagoEnum detalle;

    private Float monto;

    private Date fecha;

    public FacturaEntity(TipoPagoEnum detalle, Float monto, Date fecha) {
        this.detalle = detalle;
        this.monto = monto;
        this.fecha = fecha;
    }
}
