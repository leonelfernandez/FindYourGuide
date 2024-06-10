package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

public class PendienteState extends ReservaState{

    public PendienteState(ReservaEntity reserva) {
        super(reserva);
    }

    @Override
    public void pagarAnticipo() {
        if (!this.getState().equals(ReservaStateEnum.CONFIRMADO)) throw new IllegalStateException("La reserva tiene que ser confirmadada para pagar el anticipo");
        else this.reserva.cambiarEstado(new ConfirmadoState(this.reserva));
    }

    @Override
    public void cancelarReserva() {
        this.reserva.cambiarEstado(new CanceladoState(this.reserva));
    }

    @Override
    public void confirmarReserva() {
        this.reserva.cambiarEstado(new ConfirmadoState(this.reserva));
    }

    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.PENDIENTE;
    }
}
