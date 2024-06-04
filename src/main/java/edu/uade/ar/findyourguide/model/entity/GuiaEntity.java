package edu.uade.ar.findyourguide.model.entity;

import edu.uade.ar.findyourguide.model.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GuiaEntity extends UsuarioEntity {
    private Float puntajePromedio;
    private String credencial;
    @ManyToMany
    private List<Servicio> serviciosOfrecidos;

    @ManyToMany
    private List<CiudadEntity> ciudades;
    @ManyToMany
    private List<IdiomaEntity> idiomas;

    public GuiaEntity(String nombre, String apellido, String email, String password, String telefono, String credencial, List<CiudadEntity> ciudades, List<Servicio> serviciosOfrecidos) {
        super(nombre, apellido, email, password, telefono);
        this.credencial = credencial;
        this.ciudades = ciudades;
        this.serviciosOfrecidos = serviciosOfrecidos;
        this.puntajePromedio = 0.0f;
    }



}

