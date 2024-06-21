package edu.uade.ar.findyourguide.model.observer;

import edu.uade.ar.findyourguide.model.entity.ReseniaEntity;

public interface IObservable {
    void attach(IObserver o);
    void detach(IObserver o);
    void notificar(ReseniaEntity resenia);
}
