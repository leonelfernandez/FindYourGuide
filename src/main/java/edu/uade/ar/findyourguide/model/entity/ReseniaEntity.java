package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Entity
public class ReseniaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resenia_id_seq")
    @SequenceGenerator(name = "resenia_id_seq", sequenceName = "resenia_id_seq",  allocationSize=1)
    private Long id;
    private Integer puntuacion;
    private String comentario;
    @ManyToOne
    @JoinColumn(name = "guia_id")
    private GuiaEntity guia;
    @ManyToOne
    @JoinColumn(name = "turista_id")
    private TuristaEntity turista;
}
