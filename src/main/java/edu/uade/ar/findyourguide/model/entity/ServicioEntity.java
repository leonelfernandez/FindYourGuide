package edu.uade.ar.findyourguide.model.entity;

import edu.uade.ar.findyourguide.model.enums.ServiciosEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicios")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServicioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servicios_id_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ServiciosEnum nombre;

}