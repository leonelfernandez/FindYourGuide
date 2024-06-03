package edu.uade.ar.findyourguide.model;

public class Anticipo {

    private Float valorAnticipo;
    private IPagoAdapter pagoAdapter;

    public Anticipo() {
        this.valorAnticipo = 0.1f;
        this.pagoAdapter = new Stripe();
    }

    public String realizarPago(Float montoPagado) {
        pagoAdapter.realizarPago();
        return "Pagado";
    }


}
