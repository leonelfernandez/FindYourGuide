package edu.uade.ar.findyourguide.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tarifas")
@Builder
@Entity
public class TarifaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tarifa_id_seq")
    @SequenceGenerator(name = "tarifa_id_seq", sequenceName = "tarifa_id_seq",  allocationSize=1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "guia_id")
    private GuiaEntity guia;
    private Float precio;

    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private CiudadEntity ciudad;

    @Transient
    private Float comision;


    public TarifaEntity(GuiaEntity guia, Float precio, CiudadEntity ciudad) {
        this.guia = guia;
        this.comision = 0.10F;
        this.precio = precio + (comision * precio); //Aca va el precio del turista + la comision de la plataforma
        this.ciudad = ciudad;

    }
}
