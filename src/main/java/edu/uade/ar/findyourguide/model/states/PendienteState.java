package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

import java.util.Date;

public class PendienteState extends ReservaState{
    private final Float recargo = 1.0F;

    public PendienteState(ReservaEntity reserva) {
        super(reserva);
    }

    @Override
    public void pagar(PagoEntity pago) {
        this.reserva.agregarPago(pago);
//        if (!pago.pagarAnticipo()) throw new Error; // Adapter
        pago.pagarAnticipo();
        this.reserva.cambiarEstado(new ConfirmadoState(this.reserva));
    }

    @Override
    public void cancelarReserva(Date fechaCancelacion, PagoEntity pago)  {
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

