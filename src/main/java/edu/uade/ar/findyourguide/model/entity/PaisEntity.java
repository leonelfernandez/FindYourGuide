package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "paises")
@Entity
public class PaisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_id_seq")
    @SequenceGenerator(name = "pais_id_seq", sequenceName = "pais_id_seq",  allocationSize=1)
    private Long id;
    private String nombre;

    public PaisEntity(String nombre) {
        this.nombre = nombre;
    }
}
