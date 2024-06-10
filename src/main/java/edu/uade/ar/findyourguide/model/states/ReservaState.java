package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public abstract class ReservaState {
    ReservaEntity reserva;

    public abstract void pagarAnticipo(ReservaEntity reserva);

    public abstract void cancelarReserva(ReservaEntity reserva);


    public abstract void confirmarReserva(ReservaEntity reserva) ;

    public abstract ReservaStateEnum getState();



}
