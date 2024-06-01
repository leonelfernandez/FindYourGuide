package edu.uade.ar.findyourguide.entities;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private String sexo;
    private String telefono;

    private String urlFoto;

    public Usuario(String nombre, String apellido, Integer dni, String sexo, String telefono, String urlFoto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.sexo = sexo;
        this.telefono = telefono;
        this.urlFoto = urlFoto;
    }




}