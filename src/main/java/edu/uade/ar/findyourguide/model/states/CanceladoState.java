package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.exceptions.*;
import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

import java.util.Date;

public class CanceladoState extends ReservaState{


    public CanceladoState(ReservaEntity reserva) {
        super(reserva);
    }

    @Override
    public void pagar(PagoEntity pago) throws PagosYaRealizadosError {
        //throw error
        throw new PagosYaRealizadosError("No se puede pagar una reserva cancelada");
    }

    @Override
    public void cancelarReserva(Date fechaCancelacion) throws CancelarError {
        /*
        * Caso 1: Cancelacion previo a la aceptacion de parte del guia -> no se le cobrara nada, y se le reintegrara el anticipo. (Se hace todo en el estado previo y pasa a este todo)
        * Caso 2: Cancelacion con aceptacion previa de parte del guia -> se le cobrara un recargo N (en mi caso 10%).
        * Caso 3: Cancelacion dentro de las fechas indicadas del viaje -> se le cobrara la totalidad.
        * */

        throw new CancelarError("No se puede cancelar una reserva ya cancelada");


    }

    @Override
    public void confirmarReserva() throws ReservaConfirmadaError {
        //throw error
        throw new ReservaConfirmadaError("No se puede confirmar una reserva cancelada");
    }

    @Override
    public void rechazarReserva() throws ReservaRechazadaError {
        //throw error
        throw new ReservaRechazadaError("No se puede rechazar una reserva cancelada");
    }


    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.CANCELADO;
    }
}
