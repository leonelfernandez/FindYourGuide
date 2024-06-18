package edu.uade.ar.findyourguide.model.entity;

import edu.uade.ar.findyourguide.model.adapters.IPagoAdapter;
import edu.uade.ar.findyourguide.model.adapters.impl.Stripe;
import edu.uade.ar.findyourguide.model.enums.TipoPagoEnum;
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
    @Transient
    private Float porcentajeAnticipo;
    @Transient
    private Float porcentajeTotal = 1.0F;

    @Transient
    private IPagoAdapter pagoAdapter;

    @Enumerated(EnumType.STRING)
    private TipoPagoEnum referencia;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private ReservaEntity reserva;

    @OneToOne
    private ReintegroEntity reintegro;


    public PagoEntity(Float montoTotal, Date fechaEmision, ReservaEntity reserva, TipoPagoEnum referencia) {
        this.montoTotal = montoTotal;
        this.fechaEmision = fechaEmision;
        this.porcentajeAnticipo = 0.10F;
        this.reserva = reserva;
        this.referencia = referencia;
        this.pagoAdapter = new Stripe();
    }

    public Boolean pagarAnticipo() {
       return this.pagoAdapter.realizarPago(this.montoTotal * this.porcentajeAnticipo); //adapter realiza el pago
    }

    public Boolean pagarRestante(PagoEntity pago) {
        return this.pagoAdapter.realizarPago(pago.getMontoTotal() - (pago.getMontoTotal() * pago.getPorcentajeAnticipo()));
    }

    public Boolean pagarTotal() {
        return this.pagoAdapter.realizarPago(this.montoTotal * this.porcentajeTotal);
    }

    public Float getMontoAReintegrar() {
        return this.montoTotal * this.porcentajeAnticipo;
    }


}
