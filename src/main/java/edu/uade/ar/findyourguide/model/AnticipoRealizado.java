package edu.uade.ar.findyourguide.model;

public class AnticipoRealizado extends Anticipo{
    private Anticipo pago;
    private IPagoAdapter pagoAdapter;


    public AnticipoRealizado(Anticipo anticipo) {
        this.pago = anticipo;
    }
    public String realizarPago(Float montoPagado) {
        pagoAdapter.realizarPago();
        return "Realizado";
    }
}
