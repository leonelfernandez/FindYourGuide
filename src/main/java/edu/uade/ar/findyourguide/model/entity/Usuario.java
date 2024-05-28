package edu.uade.ar.findyourguide.model.entity;
import jakarta.persistence.Entity;

public abstract class Usuario {

    private Integer id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private String sexo;
    private String telefono;

}
