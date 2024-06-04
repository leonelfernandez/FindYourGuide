package edu.uade.ar.findyourguide.model.entity;

import edu.uade.ar.findyourguide.model.Anticipo;
import edu.uade.ar.findyourguide.model.ReservaState;
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
@Table(name = "reservas")
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_id_seq")
    private Long id;
    private GuiaEntity guia;
    private TuristaEntity turista;
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    private Float precioTotal;
    private ReservaState estado;

    private Anticipo anticipo;

    public void pagarAnticipo() {

    }

    public void cancelarReserva() {

    }

    public void confirmarReserva() {
    }


}
