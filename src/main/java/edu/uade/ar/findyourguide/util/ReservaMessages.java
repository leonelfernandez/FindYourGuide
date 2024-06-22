package edu.uade.ar.findyourguide.util;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ReservaMessages {

    public static String reservaCreadaNotif(TuristaEntity turista) {
        return "La reserva a nombre de " + turista.getNombre() + " ha sido creada.";
    }

    public static String reservaRechazada(TuristaEntity turista) {
        return "La reserva a nombre de " + turista.getNombre() + " ha sido rechazada.";
    }

    public static String chatIniciado(GuiaEntity guia) {
        return "Se ha iniciado el chat correspondiente con el guía " + guia.getNombre() + ". Correspondiente a su reserva.";
    }

    public static String chatIniciado(TuristaEntity turista) {
        return "Se ha iniciado el chat correspondiente con el turista " + turista.getNombre() + ". Correspondiente a su reserva.";
    }

    public static String chatCreado() {
        return "Se ha creado un chat correspondiente a su reserva.";
    }
}
