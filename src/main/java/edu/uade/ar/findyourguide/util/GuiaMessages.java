package edu.uade.ar.findyourguide.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuiaMessages {

    public static String credencialValidada() {
        return "Su credencial ha sido validada. Puede comenzar a ofrecer, servicios.";
    }


}
