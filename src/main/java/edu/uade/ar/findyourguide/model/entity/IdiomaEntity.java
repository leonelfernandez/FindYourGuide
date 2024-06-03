package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "idiomas")
public class IdiomaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idioma_id_seq")
    private Long id;
    private String nombre;
}
