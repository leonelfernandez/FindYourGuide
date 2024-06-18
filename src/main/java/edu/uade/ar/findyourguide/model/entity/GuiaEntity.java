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

    @ManyToMany
    private List<ServicioEntity> serviciosOfrecidos;

    @ManyToMany
    private List<IdiomaEntity> idiomas;



    public GuiaEntity(String nombre, String apellido, String email, String password, String telefono, String credencial, List<ServicioEntity> serviciosOfrecidos, String foto, String sexo, Integer dni, String fotoVerificacion) {
        super(nombre, apellido, sexo, dni, email, password, telefono, foto);
        this.credencial = credencial;
        this.serviciosOfrecidos = serviciosOfrecidos;
        this.puntajePromedio = 0.0F;
        this.fotoVerificacion = fotoVerificacion;
    }



}

