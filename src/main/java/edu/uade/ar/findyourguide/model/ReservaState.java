package edu.uade.ar.findyourguide.model;

import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
import lombok.AllArgsConstructor;


public abstract class ReservaState {
    ReservaEntity reserva;

    public ReservaState(ReservaEntity reserva) {
        this.reserva = reserva;
    }


    public abstract void pagarAnticipo();

    public abstract void cancelarReserva();


    public abstract void confirmarReserva() ;

    public abstract ReservaStateEnum getState();



}
