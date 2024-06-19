package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@SuperBuilder
public class TuristaEntity extends UsuarioEntity {
    @OneToMany(mappedBy = "turista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReseniaEntity> resenias;

    public TuristaEntity(String nombre, String apellido, String email, String password, String telefono, String sexo, Integer dni, String foto) {
        super(nombre, apellido, sexo, dni, email, password, telefono, foto);
    }

}

