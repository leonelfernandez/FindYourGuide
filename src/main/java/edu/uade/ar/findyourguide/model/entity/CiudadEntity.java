package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ciudades")
public class CiudadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ciudad_id_seq")
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "pais_id")
    private PaisEntity pais;

    @ManyToMany
    private List<GuiaEntity> guias;
}
