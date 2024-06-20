package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.exceptions.ReservaRechazadaError;
import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

import java.util.Date;

import static edu.uade.ar.findyourguide.model.enums.ReservaStateEnum.RECHAZADO;

public class RechazadoState extends ReservaState{

    public RechazadoState(ReservaEntity reserva) {
        super(reserva);
    }


    @Override
    public void pagar(PagoEntity pago) throws ReservaRechazadaError {
        throw new ReservaRechazadaError("No se puede pagar una reserva rechazada");
    }

    @Override
    public void cancelarReserva(Date fechaCancelacion) throws ReservaRechazadaError {
        throw new ReservaRechazadaError("No se puede cancelar una reserva rechazada");
    }

    @Override
    public void confirmarReserva() throws ReservaRechazadaError {
        throw new ReservaRechazadaError("No se puede confirmar una reserva rechazada");
    }

    @Override
    public void rechazarReserva() throws ReservaRechazadaError {
        throw new ReservaRechazadaError("La reserva ya fue rechazada");
    }

    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.RECHAZADO;
    }
}
