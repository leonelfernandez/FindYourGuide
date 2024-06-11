package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public abstract class ReservaState {
    ReservaEntity reserva;

    public abstract void pagarAnticipo(PagoEntity pago);

    public abstract void cancelarReserva(Date fechaCancelacion); //Deberia pasarle el estado anterior? Para contemplar los casos de cancelacion


    public abstract void confirmarReserva();
    public abstract ReservaStateEnum getState();



}
