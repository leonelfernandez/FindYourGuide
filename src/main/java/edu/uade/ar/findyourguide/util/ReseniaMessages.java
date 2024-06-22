package edu.uade.ar.findyourguide.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ReseniaMessages {

    public static String trofeoObtenido() {
        return "¡Felicidades! Has obtenido un nuevo trofeo.";
    }

    public static String reseniaCreada() {
        return "La reseña fue creada.";
    }
}
