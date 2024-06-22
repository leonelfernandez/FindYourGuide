package edu.uade.ar.findyourguide.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class TuristaMessages {


    public static String calificaFindYourGuide() {
        return "Califica tu experiencia con FindYourGuide";
    }

}
