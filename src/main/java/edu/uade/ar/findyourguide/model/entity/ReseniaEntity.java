package edu.uade.ar.findyourguide.model.entity;

import edu.uade.ar.findyourguide.model.observer.IObservable;
import edu.uade.ar.findyourguide.model.observer.IObserver;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Entity
public class ReseniaEntity implements IObservable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resenia_id_seq")
    @SequenceGenerator(name = "resenia_id_seq", sequenceName = "resenia_id_seq", allocationSize = 1)
    private Long id;
    private Integer puntuacion;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "guia_id")
    private GuiaEntity guia;

    @ManyToOne
    @JoinColumn(name = "turista_id")
    private TuristaEntity turista;

    @Transient
    private List<IObserver> trofeos = new ArrayList<>();

    @Override
    public void attach(IObserver o) {
        trofeos.add(o);
    }

    @Override
    public void detach(IObserver o) {
        trofeos.remove(o);
    }

    @Override
    public void notificar(ReseniaEntity resenia) {
        for (IObserver trofeo : trofeos) {
            trofeo.actualizar(resenia);
        }
    }
}
