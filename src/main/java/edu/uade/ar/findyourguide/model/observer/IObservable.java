package edu.uade.ar.findyourguide.model.observer;

import edu.uade.ar.findyourguide.model.entity.ReseniaEntity;

public interface IObservable {

    void attach(IObserver observer);

    void detach(IObserver observer);

    void notificar(ReseniaEntity resenia);


}
