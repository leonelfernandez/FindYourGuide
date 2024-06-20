package edu.uade.ar.findyourguide.model.entity;

import edu.uade.ar.findyourguide.model.enums.ServiciosEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "servicios")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServicioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servicios_id_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ServiciosEnum nombre;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicioEntity that = (ServicioEntity) o;
        return Objects.equals(id, that.id) &&
                nombre == that.nombre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }


}