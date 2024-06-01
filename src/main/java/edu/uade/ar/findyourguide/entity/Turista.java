package edu.uade.ar.findyourguide.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "turistas")
public class Turista extends Usuario{

    public Turista(String nombre, String apellido, Integer dni, String sexo, String telefono, String urlFoto) {
        super(nombre, apellido, dni, sexo, telefono, urlFoto);
    }

}
