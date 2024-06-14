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
    public void pagar(PagoEntity pago) {
        this.reserva.agregarPago(pago);
        pago.pagarRestante(pago);
        this.reserva.cambiarEstado(new FinalizadoState(this.reserva));
    }

    @Override
    public void cancelarReserva(Date fechaCancelacion) {
        //Pagar el % de recargo indicado y en caso de estar en las fechas del viaje, pagar el total

        this.reserva.cambiarEstado(new CanceladoState(this.reserva));

    }

    @Override
    public void confirmarReserva() {
        //ya fue confirmada, throw error
    }

    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.RESERVADO;
    }

}
