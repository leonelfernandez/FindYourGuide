package edu.uade.ar.findyourguide.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "viajes")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "viaje_id_seq")
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guia_id")
    private Guia guia;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "turista_id")
    private Turista turista;

    private String ciudad;
    private Date fechaInicio;
    private Date fechaFin;

    private ReservaState reservaState;
    private ServicioAgregado servicioAgregado;

    public void pagarAnticipo(Float monto) {

    }

    public void cambiarEstado(ReservaState reservaState) {
        this.reservaState = reservaState;
    }




}
