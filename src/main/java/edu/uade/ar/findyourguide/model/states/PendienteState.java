package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

public class PendienteState extends ReservaState{

    public PendienteState(ReservaEntity reserva) {
        super(reserva);
    }

    @Override
    public void pagarAnticipo() {

    }

    @Override
    public void cancelarReserva() {

    }

    @Override
    public void confirmarReserva() {

    }

    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.PENDIENTE;
    }
}
