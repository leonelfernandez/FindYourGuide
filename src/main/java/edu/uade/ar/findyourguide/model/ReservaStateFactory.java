package edu.uade.ar.findyourguide.model;

import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

public class ReservaStateFactory {

    public static ReservaState getReservaState(ReservaStateEnum reservaStateEnum, ReservaEntity reservaEntity){
        return switch (reservaStateEnum) {
            case PENDIENTE -> new PendienteState(reservaEntity);
            case CONFIRMADO -> new ConfirmadoState(reservaEntity);
            case RESERVADO -> new ReservadoState(reservaEntity);
            case CANCELADO -> new CanceladoState(reservaEntity);
            default -> throw new IllegalArgumentException("Unknown state: " + reservaStateEnum);
        };
    }




}
