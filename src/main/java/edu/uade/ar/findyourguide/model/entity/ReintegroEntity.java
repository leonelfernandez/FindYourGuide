package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reintegros")
@Builder
public class ReintegroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reintegro_id_seq")
    @SequenceGenerator(name = "reintegro_id_seq", sequenceName = "reintegro_id_seq",  allocationSize=1)
    private Long id;
    private Float montoReintegrado;
    @Temporal(TemporalType.DATE)
    private Date fechaReintegro;

    @OneToOne
    private PagoEntity pago;

    public ReintegroEntity(Float montoReintegrado, Date fechaReintegro, PagoEntity pago) {
        this.montoReintegrado = montoReintegrado;
        this.fechaReintegro = fechaReintegro;
        this.pago = pago;
    }
}
