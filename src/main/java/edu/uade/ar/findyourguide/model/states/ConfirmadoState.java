package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
import edu.uade.ar.findyourguide.repository.ReintegroRepository;

import java.util.Date;

public class ConfirmadoState extends ReservaState{

    //private ReintegroRepository reintegroRepository;

    public ConfirmadoState(ReservaEntity reserva) {
        super(reserva);
    }

    @Override
    public void pagar(PagoEntity pago) {
        //Ya esta pago el anticipo, asi que tirale error
        //throw
    }

    @Override
    public void cancelarReserva(Date fechaCancelacion, PagoEntity pago) {
        //hay reintegro?
        this.reserva.cambiarEstado(new CanceladoState(this.reserva));
    }

    @Override
    public void confirmarReserva() {
        //Abrir el chat
        this.reserva.cambiarEstado(new ReservadoState(this.reserva));
    }

    @Override
    public void rechazarReserva() {
        this.reserva.cambiarEstado(new RechazadoState(this.reserva));
    }

    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.CONFIRMADO;
    }
}
