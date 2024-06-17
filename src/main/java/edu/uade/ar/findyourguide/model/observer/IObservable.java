package edu.uade.ar.findyourguide.model.observer;

public interface IObservable {
    void attach(IObserver o);
    void detach(IObserver o);
    void notificar();
}
