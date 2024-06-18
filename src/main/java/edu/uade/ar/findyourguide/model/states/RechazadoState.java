package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

import java.util.Date;

import static edu.uade.ar.findyourguide.model.enums.ReservaStateEnum.RECHAZADO;

public class RechazadoState extends ReservaState{

    public RechazadoState(ReservaEntity reserva) {
        super(reserva);
    }

    public RechazadoState() {
    }

    @Override
    public void pagar(PagoEntity pago) {
        //throw errror
    }

    @Override
    public void cancelarReserva(Date fechaCancelacion, PagoEntity pago) {
        //throw errror
    }

    @Override
    public void confirmarReserva() {
        //throw errror
    }

    @Override
    public void rechazarReserva() {
        //throw errror
    }

    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.RECHAZADO;
    }
}
