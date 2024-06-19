package edu.uade.ar.findyourguide.model.entity;

import edu.uade.ar.findyourguide.model.adapters.IPagoAdapter;
import edu.uade.ar.findyourguide.model.adapters.impl.Stripe;
import edu.uade.ar.findyourguide.model.enums.TipoPagoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pagos")
@ToString
public class PagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pago_id_seq")
    @SequenceGenerator(name = "pago_id_seq", sequenceName = "pago_id_seq",  allocationSize=1)
    private Long id;
    private Float montoTotal;
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;

    @Enumerated(EnumType.STRING)
    private TipoPagoEnum referencia;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private ReservaEntity reserva;

//    @OneToOne
//    private ReintegroEntity reintegro;


    public PagoEntity(Float montoTotal, Date fechaEmision, ReservaEntity reserva, TipoPagoEnum referencia) {
        this.montoTotal = montoTotal;
        this.fechaEmision = fechaEmision;
        this.reserva = reserva;
        this.referencia = referencia;
    }




}
