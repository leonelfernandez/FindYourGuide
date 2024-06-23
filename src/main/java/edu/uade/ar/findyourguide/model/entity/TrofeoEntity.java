package edu.uade.ar.findyourguide.model.entity;

import edu.uade.ar.findyourguide.model.enums.TipoTrofeoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "trofeos")
@Entity

public class TrofeoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trofeo_id_seq")
    @SequenceGenerator(name = "trofeo_id_seq", sequenceName = "trofeo_id_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTrofeoEnum tipo;

    @ManyToMany(mappedBy = "trofeos")
    private List<UsuarioEntity> usuarios;

}
