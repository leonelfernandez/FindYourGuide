package edu.uade.ar.findyourguide.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@SuperBuilder
public class GuiaEntity extends UsuarioEntity {
    private Float puntajePromedio = 0.0F;
    private String credencial;

    private String fotoVerificacion;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<ServicioEntity> serviciosOfrecidos;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<IdiomaEntity> idiomas;


    public GuiaEntity(String nombre, String apellido, String sexo, Integer dni, String email, String password, String telefono, String foto, Float puntajePromedio, String credencial, String fotoVerificacion, List<ServicioEntity> serviciosOfrecidos, List<IdiomaEntity> idiomas) {
        super(nombre, apellido, sexo, dni, email, password, telefono, foto);
        this.puntajePromedio = 0.0F;
        this.credencial = credencial;
        this.fotoVerificacion = fotoVerificacion;
        this.serviciosOfrecidos = serviciosOfrecidos;
        this.idiomas = idiomas;
    }
}

