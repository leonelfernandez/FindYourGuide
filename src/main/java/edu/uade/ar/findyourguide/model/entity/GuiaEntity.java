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
    private Float puntajePromedio;
    private String credencial;

    private String fotoVerificacion;
    @ManyToMany
    private List<ServicioEntity> serviciosOfrecidos;

    @ManyToMany
    private List<CiudadEntity> ciudades;
    @ManyToMany
    private List<IdiomaEntity> idiomas;
    @OneToMany(mappedBy = "guia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReseniaEntity> resenias;

    public GuiaEntity(String nombre, String apellido, String email, String password, String telefono, String credencial, List<CiudadEntity> ciudades, List<ServicioEntity> serviciosOfrecidos, String foto, String sexo, Integer dni, String fotoVerificacion) {
        super(nombre, apellido, sexo, dni, email, password, telefono, foto);
        this.credencial = credencial;
        this.ciudades = ciudades;
        this.serviciosOfrecidos = serviciosOfrecidos;
        this.puntajePromedio = 0.0f;
        this.fotoVerificacion = fotoVerificacion;
    }



}

