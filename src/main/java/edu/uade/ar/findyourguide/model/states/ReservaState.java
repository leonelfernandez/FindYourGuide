package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.exceptions.AnticipoPagadoError;
import edu.uade.ar.findyourguide.exceptions.PagoNoRealizadoError;
import edu.uade.ar.findyourguide.exceptions.ReservaConfirmadaError;
import edu.uade.ar.findyourguide.exceptions.ReservaFinalizadaError;
import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.ReservaStateEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public abstract class ReservaState {
    protected ReservaEntity reserva;

    public abstract void pagar(PagoEntity pago) throws AnticipoPagadoError, ReservaFinalizadaError;

    public abstract void cancelarReserva(Date fechaCancelacion) throws ReservaFinalizadaError;


    public abstract void confirmarReserva() throws PagoNoRealizadoError, ReservaConfirmadaError, ReservaFinalizadaError;

    public abstract void rechazarReserva() throws PagoNoRealizadoError, ReservaFinalizadaError, ReservaConfirmadaError;

    public abstract ReservaStateEnum getState();



}
