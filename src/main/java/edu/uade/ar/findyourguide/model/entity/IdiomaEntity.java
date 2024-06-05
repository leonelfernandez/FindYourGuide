package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "idiomas")
@Entity
public class IdiomaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idioma_id_seq")
    @SequenceGenerator(name = "idioma_id_seq", sequenceName = "idioma_id_seq",  allocationSize=1)
    private Long id;
    private String nombre;
}
