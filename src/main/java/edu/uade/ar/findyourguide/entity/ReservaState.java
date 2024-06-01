package edu.uade.ar.findyourguide.entity;


public abstract class ReservaState {

    private Viaje viaje;
    public ReservaState(Viaje viaje) {
        this.viaje = viaje;
    }

    public abstract void pagarAnticipo();


}
