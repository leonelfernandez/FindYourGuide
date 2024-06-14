package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

import java.util.Date;

public class PendienteState extends ReservaState{

    public PendienteState(ReservaEntity reserva) {
        super(reserva);
    }

    @Override
    public void pagar(PagoEntity pago) {
        this.reserva.agregarPago(pago);
        pago.pagarAnticipo(); // Adapter
        this.reserva.cambiarEstado(new ConfirmadoState(this.reserva));
    }

    @Override
    public void cancelarReserva(Date fechaCancelacion)  {
        this.reserva.cambiarEstado(new CanceladoState(this.reserva));
    }

    @Override
    public void confirmarReserva() {
        //throw error
    }

    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.PENDIENTE;
    }



}

