package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

import java.util.Date;

public class FinalizadoState extends ReservaState{

    public FinalizadoState(ReservaEntity reserva) {
        super(reserva);
    }

    @Override
    public void pagar(PagoEntity pago) {
        //throw
    }

    @Override
    public void cancelarReserva(Date fechaCancelacion, PagoEntity pago) {
        //throw
    }

    @Override
    public void confirmarReserva() {
        //throw

    }

    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.FINALIZADO;
    }
}
