package edu.uade.ar.findyourguide.model;

import jakarta.persistence.*;

@Entity
@Table(name = "servicios")
public abstract class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servicios_id_seq")
    private Long id;

    public abstract String getServicioContratado();

}