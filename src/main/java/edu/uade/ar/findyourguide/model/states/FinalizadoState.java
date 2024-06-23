package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.exceptions.FinalizadoError;
import edu.uade.ar.findyourguide.exceptions.ReservaFinalizadaError;
import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

import java.util.Date;

public class FinalizadoState extends ReservaState{

    public FinalizadoState(ReservaEntity reserva) {
        super(reserva);
    }

    @Override
    public void pagar(PagoEntity pago) throws ReservaFinalizadaError {
        throw new ReservaFinalizadaError("La reserva ya fue finalizada");
    }

    @Override
    public void cancelarReserva(Date fechaCancelacion) throws ReservaFinalizadaError {
        throw new ReservaFinalizadaError("La reserva ya fue finalizada");
    }

    @Override
    public void confirmarReserva() throws ReservaFinalizadaError {
        throw new ReservaFinalizadaError("La reserva ya fue finalizada");

    }

    @Override
    public void rechazarReserva() throws ReservaFinalizadaError {
        throw new ReservaFinalizadaError("La reserva ya fue finalizada");
    }

    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.FINALIZADO;
    }

    @Override
    public void finalizarReserva() throws FinalizadoError {
        throw new FinalizadoError("Error");
    }
}
