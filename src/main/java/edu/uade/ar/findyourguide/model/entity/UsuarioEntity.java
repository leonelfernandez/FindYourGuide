package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "usuarios")
public abstract class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
    private Long id;
    private String nombre;
    private String apellido;
    private String sexo;
    private Integer dni;
    private String email;
    private String password;
    private String telefono;
    private String foto;


    public UsuarioEntity(String nombre, String apellido, String email, String password, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }
}
