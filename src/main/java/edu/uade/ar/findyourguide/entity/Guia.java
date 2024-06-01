package edu.uade.ar.findyourguide.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@Table(name = "guias")
public class Guia extends Usuario{

    private Float promPuntuacion;
    private Viaje viajesRealizados;
    private String idiomas;
    private String credencial;

    public Guia(String nombre, String apellido, Integer dni, String sexo, String telefono,String urlFoto, String credencial, String idiomas) {
        super(nombre, apellido, dni, sexo, telefono, urlFoto);
        this.promPuntuacion = 0F;
        this.viajesRealizados = null;
        this.credencial = credencial;
        this.idiomas = idiomas;
    }

}
