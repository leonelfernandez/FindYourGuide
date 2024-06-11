package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

public class CanceladoState extends ReservaState{
    private Float recargo = 0.1F;
    private Float recargoTotalMonto = 1.0F;


    public CanceladoState(ReservaEntity reserva) {
        super(reserva);
    }

    @Override
    public void cancelarReserva() {
        /*
        * Caso 1: Cancelacion previo a la aceptacion de parte del guia -> no se le cobrara nada, y se le reintegrara el anticipo.
        * Caso 2: Cancelacion con aceptacion previa de parte del guia -> se le cobrara un recargo N (en mi caso 10%).
        * Caso 3: Cancelacion dentro de las fechas indicadas del viaje -> se le cobrara la totalidad.
        * */
    }


    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.CANCELADO;
    }
}
