package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "idiomas")
@Entity
public class IdiomaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idioma_id_seq")
    @SequenceGenerator(name = "idioma_id_seq", sequenceName = "idioma_id_seq",  allocationSize=1)
    private Long id;
    private String nombre;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdiomaEntity that = (IdiomaEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }


}
