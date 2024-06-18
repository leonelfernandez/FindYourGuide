package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

import java.util.Date;

public class CanceladoState extends ReservaState{


    public CanceladoState(ReservaEntity reserva) {
        super(reserva);
    }

    @Override
    public void pagar(PagoEntity pago) {
        //throw error
    }

    @Override
    public void cancelarReserva(Date fechaCancelacion, PagoEntity pago) {
        /*
        * Caso 1: Cancelacion previo a la aceptacion de parte del guia -> no se le cobrara nada, y se le reintegrara el anticipo. (Se hace todo en el estado previo y pasa a este todo)
        * Caso 2: Cancelacion con aceptacion previa de parte del guia -> se le cobrara un recargo N (en mi caso 10%).
        * Caso 3: Cancelacion dentro de las fechas indicadas del viaje -> se le cobrara la totalidad.
        * */

        //Throw error


    }

    @Override
    public void confirmarReserva() {
        //throw error
    }

    @Override
    public void rechazarReserva() {
        //throw error
    }


    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.CANCELADO;
    }
}
