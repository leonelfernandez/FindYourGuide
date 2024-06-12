package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

import java.util.Date;

public class ReservadoState extends ReservaState{


    public ReservadoState(ReservaEntity reserva) {
        super(reserva);
    }


    @Override
    public void pagarAnticipo(PagoEntity pago) {
        //throw error

    }

    @Override
    public void cancelarReserva(Date fechaCancelacion) {
        //Reintegro total

        this.reserva.cambiarEstado(new CanceladoState(this.reserva));

    }

    @Override
    public void confirmarReserva() {

    }

    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.RESERVADO;
    }

}
