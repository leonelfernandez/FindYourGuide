package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public abstract class ReservaState {
    ReservaEntity reserva;

    public void pagarAnticipo() {}

    public void cancelarReserva(){}


    public void confirmarReserva() {}

    public abstract ReservaStateEnum getState();



}
