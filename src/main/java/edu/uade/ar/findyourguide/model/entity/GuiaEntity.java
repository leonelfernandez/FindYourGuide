package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
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
    @JoinTable(
            name = "guia_servicio",
            joinColumns = @JoinColumn(name = "guia_id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id")
    )
    private List<ServicioEntity> serviciosOfrecidos;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "guia_idioma",
            joinColumns = @JoinColumn(name = "guia_id"),
            inverseJoinColumns = @JoinColumn(name = "idioma_id")
    )
    private List<IdiomaEntity> idiomas;

    @OneToMany(mappedBy = "guia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CiudadEntity> ciudades;

    @OneToMany(mappedBy = "guia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReseniaEntity> resenias;

    public GuiaEntity(String nombre, String apellido, String email, String password, String telefono, String credencial, List<ServicioEntity> serviciosOfrecidos, String foto, String sexo, Integer dni, String fotoVerificacion) {
        super(nombre, apellido, sexo, dni, email, password, telefono, foto);
        this.credencial = credencial;
        this.serviciosOfrecidos = serviciosOfrecidos;
        this.puntajePromedio = 0.0F;
        this.fotoVerificacion = fotoVerificacion;
        this.ciudades = new ArrayList<>();
        this.resenias = new ArrayList<>();
    }
}
