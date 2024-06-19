package edu.uade.ar.findyourguide.model.entity;

import edu.uade.ar.findyourguide.model.enums.TrofeoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "trofeo")
@Entity
public class TrofeoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trofeo_id_seq")
    @SequenceGenerator(name = "trofeo_id_seq", sequenceName = "trofeo_id_seq",  allocationSize=1)
    private Long id;
    @Enumerated
    private TrofeoEnum tipo;
    @OneToMany
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;
}
