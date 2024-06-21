package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@SuperBuilder
public class TuristaEntity extends UsuarioEntity {

    public TuristaEntity(String nombre, String apellido, String email, String password, String telefono, String sexo, Integer dni, String foto) {
        super(nombre, apellido, sexo, dni, email, password, telefono, foto);
    }


}

