package edu.uade.ar.findyourguide.model.entity;

import edu.uade.ar.findyourguide.model.adapters.IPagoAdapter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
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
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;
    private Float porcentajeAnticipo;

    @Transient
    private IPagoAdapter pagoAdapter;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private ReservaEntity reserva;

    public PagoEntity(Float montoTotal, Date fechaEmision, ReservaEntity reserva) {
        this.montoTotal = montoTotal;
        this.fechaEmision = fechaEmision;
        this.porcentajeAnticipo = 0.10F;
        this.reserva = reserva;
    }

    public void pagarAnticipo() {
       this.pagoAdapter.realizarPago(this.montoTotal * this.porcentajeAnticipo); //adapter realiza el pago
    }

}
