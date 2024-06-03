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
@Table(name = "guias")
public class GuiaEntity extends UsuarioEntity {
    private Float puntajePromedio;
    private String credencial;
    private Servicio servicioOfrecido;

    private List<CiudadEntity> ciudades;
    private List<IdiomaEntity> idiomas;

    public GuiaEntity(String nombre, String apellido, String email, String password, String telefono, String credencial, List<CiudadEntity> ciudades, Servicio servicioOfrecido) {
        super(nombre, apellido, email, password, telefono);
        this.credencial = credencial;
        this.ciudades = ciudades;
        this.servicioOfrecido = servicioOfrecido; // Es correcto?
        this.puntajePromedio = 0.0f;
    }



}

