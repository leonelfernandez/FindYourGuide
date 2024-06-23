package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.exceptions.AnticipoPagadoError;
import edu.uade.ar.findyourguide.exceptions.FinalizadoError;
import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
import edu.uade.ar.findyourguide.repository.ReintegroRepository;

import java.util.Date;

public class ConfirmadoState extends ReservaState{

    public ConfirmadoState(ReservaEntity reserva) {
        super(reserva);
    }

    @Override
    public void pagar(PagoEntity pago) throws AnticipoPagadoError {
        throw new AnticipoPagadoError("Ya se ha pagado el anticipo, esperar confirmacion del guia.");
    }

    @Override
    public void cancelarReserva(Date fechaCancelacion) {
        this.reserva.setEstado(ReservaStateEnum.CANCELADO);
        this.reserva.cambiarEstado(new CanceladoState(this.reserva));
    }

    @Override
    public void confirmarReserva() {
        //Abrir el chat
        this.reserva.setEstado(ReservaStateEnum.RESERVADO);
        this.reserva.cambiarEstado(new ReservadoState(this.reserva));
    }

    @Override
    public void rechazarReserva() {
        this.reserva.setEstado(ReservaStateEnum.RECHAZADO);
        this.reserva.cambiarEstado(new RechazadoState(this.reserva));
    }

    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.CONFIRMADO;
    }

    @Override
    public void finalizarReserva() throws FinalizadoError {
        throw new FinalizadoError("Error");
    }
}
