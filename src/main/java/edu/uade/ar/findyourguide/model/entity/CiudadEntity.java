package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ciudades")
public class CiudadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ciudad_id_seq")
    @SequenceGenerator(name = "ciudad_id_seq", sequenceName = "ciudad_id_seq", allocationSize = 1)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "pais_id")
    private PaisEntity pais;

    @ManyToOne
    @JoinColumn(name = "guia_id")
    private GuiaEntity guia;
}
