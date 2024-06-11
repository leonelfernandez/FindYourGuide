package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;

import java.util.Date;

public class CanceladoState extends ReservaState{
    private Float recargo = 0.1F;
    private Float recargoTotalMonto = 1.0F;


    public CanceladoState(ReservaEntity reserva) {
        super(reserva);
    }

    @Override
    public void pagarAnticipo(PagoEntity pago) {}

    @Override
    public void cancelarReserva(Date fechaCancelacion) {
        /*
        * Caso 1: Cancelacion previo a la aceptacion de parte del guia -> no se le cobrara nada, y se le reintegrara el anticipo. (Utilizacion de Enum por parametro para conocer el estado previo)
        * Caso 2: Cancelacion con aceptacion previa de parte del guia -> se le cobrara un recargo N (en mi caso 10%).
        * Caso 3: Cancelacion dentro de las fechas indicadas del viaje -> se le cobrara la totalidad.
        * */

    }

    @Override
    public void confirmarReserva() {}


    @Override
    public ReservaStateEnum getState() {
        return ReservaStateEnum.CANCELADO;
    }
}
