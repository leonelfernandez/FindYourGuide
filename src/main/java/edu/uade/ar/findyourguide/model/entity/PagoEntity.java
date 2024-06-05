package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pagos")
public class PagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pago_id_seq")
    @SequenceGenerator(name = "pago_id_seq", sequenceName = "pago_id_seq",  allocationSize=1)
    private Long id;
    private Float montoTotal;
    private Date fechaEmision;
    private Float porcentajeAnticipo = 0.10F;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private ReservaEntity reserva;


}
