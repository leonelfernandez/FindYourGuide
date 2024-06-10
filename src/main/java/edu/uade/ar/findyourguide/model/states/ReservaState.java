package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public abstract class ReservaState {
    ReservaEntity reserva;

    public abstract void pagarAnticipo();

    public abstract void cancelarReserva();


    public abstract void confirmarReserva() ;

    public abstract ReservaStateEnum getState();



}
