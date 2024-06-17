package edu.uade.ar.findyourguide.model.entity;

import edu.uade.ar.findyourguide.model.enums.TrofeoEnum;
import edu.uade.ar.findyourguide.model.observer.IObserver;
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
public class TrofeoEntity implements IObserver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trofeo_id_seq")
    @SequenceGenerator(name = "trofeo_id_seq", sequenceName = "trofeo_id_seq",  allocationSize=1)
    private Long id;
    @Enumerated
    private TrofeoEnum tipo;
    @OneToMany
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @Override
    public void actualizar(){
        if (usuario instanceof GuiaEntity) {
            calcularTrofeoExito((GuiaEntity) usuario);
        } else if (usuario instanceof TuristaEntity) {
            calcularTrofeoResena((TuristaEntity) usuario);
        }
    };

    private void calcularTrofeoExito(GuiaEntity guia) {
        int totalResenias = guia.getResenias().size();
        double puntuacionPromedio = guia.getResenias().stream()
                .mapToInt(ReseniaEntity::getPuntuacion)
                .average()
                .orElse(0.0);

        if (totalResenias >= 10 && puntuacionPromedio >= 4.5) {
            this.tipo = TrofeoEnum.EXITO;
            this.usuario = guia.getId();
        }
    }

    private void calcularTrofeoResena(TuristaEntity turista) {
        int totalResenias = turista.getResenias().size();

        if (totalResenias > 10) {
            this.tipo = TrofeoEnum.RESENIA;
            this.usuario = turista.getId();
        }
    }

}
