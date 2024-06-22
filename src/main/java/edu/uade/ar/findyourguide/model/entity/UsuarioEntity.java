package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "usuarios")
public abstract class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
    @SequenceGenerator(name = "usuario_id_seq", sequenceName = "usuario_id_seq",  allocationSize=1)
    private Long id;
    private String nombre;
    private String apellido;
    private String sexo;
    @Column(unique = true)
    private Integer dni;
    @Column(unique = true)
    private String email;
    private String password;
    private String telefono;
    private String foto;

    @ManyToMany
    private List<TrofeoEntity> trofeos = new ArrayList<>();


    public UsuarioEntity(String nombre, String apellido, String sexo, Integer dni, String email, String password, String telefono, String foto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.foto = foto;
    }
}
