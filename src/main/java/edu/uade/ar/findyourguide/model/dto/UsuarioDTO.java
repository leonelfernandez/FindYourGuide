package edu.uade.ar.findyourguide.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String sexo;
    private Integer dni;
    private String email;
    private String password;
    private String telefono;
    private String foto;




}
