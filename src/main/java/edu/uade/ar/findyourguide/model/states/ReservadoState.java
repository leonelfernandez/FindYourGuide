package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

public class ReservadoState extends ReservaState{


    public ReservadoState(ReservaEntity reserva) {
        super(reserva);
    }


    @Override
    public void pagarAnticipo(ReservaEntity reserva) {

    }

    @Override
    public void cancelarReserva(ReservaEntity reserva) {

    }

    @Override
    public void confirmarReserva(ReservaEntity reserva) {

    }

    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.RESERVADO;
    }

}
