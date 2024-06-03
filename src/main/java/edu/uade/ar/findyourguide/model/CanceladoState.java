package edu.uade.ar.findyourguide.model;

public class CanceladoState extends ReservaState{
    private Float recargo;
    private Float recargoTotalMonto;

    public CanceladoState() {
        this.recargo = 0.1f;
        this.recargoTotalMonto = 1.0f;
    }
    @Override
    public void pagarAnticipo() {

    }

    @Override
    public void cancelarReserva() {

    }

    @Override
    public void confirmarReserva() {

    }
}
