package edu.uade.ar.findyourguide.model.entity;

import edu.uade.ar.findyourguide.exceptions.AnticipoPagadoError;
import edu.uade.ar.findyourguide.exceptions.PagoNoRealizadoError;
import edu.uade.ar.findyourguide.exceptions.ReservaConfirmadaError;
import edu.uade.ar.findyourguide.exceptions.ReservaFinalizadaError;
import edu.uade.ar.findyourguide.model.states.PendienteState;
import edu.uade.ar.findyourguide.model.states.ReservaState;
import edu.uade.ar.findyourguide.model.factory.ReservaStateFactory;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reservas")
@Entity
@ToString
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_id_seq")
    @SequenceGenerator(name = "reserva_id_seq", sequenceName = "reserva_id_seq",  allocationSize=1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "guia_id")
    private GuiaEntity guia;
    @ManyToOne
    @JoinColumn(name = "turista_id")
    private TuristaEntity turista;

    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private CiudadEntity ciudad;

    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @Enumerated(EnumType.STRING)
    private ReservaStateEnum estado;
    @Transient
    private ReservaState estadoHandler = new PendienteState(this);

    @OneToMany
    private List<PagoEntity> pagos;
    @OneToMany
    private List<ServicioEntity> serviciosContratados;




    public ReservaEntity(GuiaEntity guia, TuristaEntity turista, ReservaStateEnum estado, ReservaState estadoHandler, List<PagoEntity> pagos, List<ServicioEntity> serviciosContratados) {
        this.guia = guia;
        this.turista = turista;
        this.estado = estado;
        this.estadoHandler = estadoHandler;
        this.pagos = pagos;
        this.serviciosContratados = serviciosContratados;
    }

    public void pagar(PagoEntity pago) throws AnticipoPagadoError, ReservaFinalizadaError {
        this.estadoHandler.pagar(pago);
    }

    public void cancelarReserva(Date fechaCancelacion) throws ReservaFinalizadaError {
        this.estadoHandler.cancelarReserva(fechaCancelacion);
    }

    public void confirmarReserva() throws PagoNoRealizadoError, ReservaConfirmadaError, ReservaFinalizadaError {
        this.estadoHandler.confirmarReserva();
    }
    public void rechazarReserva() throws PagoNoRealizadoError, ReservaFinalizadaError, ReservaConfirmadaError {
        this.estadoHandler.rechazarReserva();
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
