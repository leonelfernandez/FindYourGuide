package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "turistas")
public class TuristaEntity extends UsuarioEntity {

    public TuristaEntity(String nombre, String apellido, String email, String password, String telefono) {
        super(nombre, apellido, email, password, telefono);
    }


}

