package edu.uade.ar.findyourguide.model.entity;

import edu.uade.ar.findyourguide.model.states.PendienteState;
import edu.uade.ar.findyourguide.model.states.ReservaState;
import edu.uade.ar.findyourguide.model.factory.ReservaStateFactory;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reservas")
@Entity
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_id_seq")
    @SequenceGenerator(name = "reserva_id_seq", sequenceName = "reserva_id_seq",  allocationSize=1)
    private Long id;
    @OneToOne
    @JoinColumn(name = "guia_id")
    private GuiaEntity guia;
    @OneToOne
    @JoinColumn(name = "turista_id")
    private TuristaEntity turista;
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    private Float precioTotal;

    @Enumerated(EnumType.STRING)
    private ReservaStateEnum estado;
    @Transient
    private ReservaState estadoHandler = new PendienteState(this);

    @OneToOne
    @JoinColumn(name = "ciudad_id")
    private CiudadEntity ciudadDestino;

    @OneToMany
    @JoinColumn(name = "pago_id")
    private List<PagoEntity> pagos;



    public void pagarAnticipo(PagoEntity pago) {
        this.estadoHandler.pagarAnticipo(pago);
    }

    public void cancelarReserva() {
        //
    }

    public void confirmarReserva() {
        //
    }

    public void cambiarEstado(ReservaState estado) {
        this.estadoHandler = estado;
    }

    @PrePersist
    @PreUpdate
    public void preSave() {
        this.estado = this.estadoHandler.getState();
    }

    @PostLoad
    public void postLoad() {
        this.estadoHandler = ReservaStateFactory.getReservaState(this.estado, this);
    }


    public void agregarPago(PagoEntity pago) {
        this.pagos.add(pago);
    }
}
