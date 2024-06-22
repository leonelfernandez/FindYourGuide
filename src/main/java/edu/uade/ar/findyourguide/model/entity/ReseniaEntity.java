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

@Data
@NoArgsConstructor
@Entity
@Table(name = "resenias")
public class ReseniaEntity implements IObservable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resenia_id_seq")
    @SequenceGenerator(name = "resenia_id_seq", sequenceName = "resenia_id_seq",  allocationSize=1)
    private Long id;

    @ManyToOne
    private GuiaEntity guia;
    @ManyToOne
    private TuristaEntity turista;

    private String comentario;

    private Float puntuacion;

    @Transient
    private List<IObserver> trofeos = new ArrayList<>();

    public ReseniaEntity(Long id, GuiaEntity guia, TuristaEntity turista, String comentario, Float puntuacion) {
        this.id = id;
        this.guia = guia;
        this.turista = turista;
        this.comentario = comentario;
        this.puntuacion = puntuacion;
    }

    @Override
    public void attach(IObserver observer) {
        trofeos.add(observer);

    }

    @Override
    public void detach(IObserver observer) {
        trofeos.remove(observer);

    }

    @Override
    public void notificar(ReseniaEntity resenia) {
        this.trofeos.forEach(trofeo -> trofeo.actualizar(resenia));

    }
}
