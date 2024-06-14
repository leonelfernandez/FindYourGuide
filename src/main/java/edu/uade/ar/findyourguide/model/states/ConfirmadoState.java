package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

import java.util.Date;

public class ConfirmadoState extends ReservaState{

    private final Float recargo = 0.1F;

    public ConfirmadoState(ReservaEntity reserva) {
        super(reserva);
    }

    @Override
    public void pagar(PagoEntity pago) {
        //Ya esta pago el anticipo, asi que tirale error

    }

    @Override
    public void cancelarReserva(Date fechaCancelacion) {
        //Reintegro del anticipo
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
