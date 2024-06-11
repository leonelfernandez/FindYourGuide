package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

public class ConfirmadoState extends ReservaState{

    public ConfirmadoState(ReservaEntity reserva) {
        super(reserva);
    }

    @Override
    public void cancelarReserva() {
        this.reserva.cambiarEstado(new CanceladoState(this.reserva));
    }

    @Override
    public void confirmarReserva() {
        this.reserva.cambiarEstado(new ReservadoState(this.reserva));
    }

    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.CONFIRMADO;
    }
}
