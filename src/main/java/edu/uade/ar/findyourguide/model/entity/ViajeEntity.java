package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "viajes")
public class ViajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "viaje_id_seq")
    @SequenceGenerator(name = "viaje_id_seq", sequenceName = "viaje_id_seq",  allocationSize=1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private CiudadEntity destino;
    private Float precio;
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @OneToOne(cascade = CascadeType.ALL) //Error
    private ReservaEntity reserva;



}
