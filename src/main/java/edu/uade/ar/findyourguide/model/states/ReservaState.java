package edu.uade.ar.findyourguide.model.states;

import edu.uade.ar.findyourguide.exceptions.*;
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

    public abstract void pagar(PagoEntity pago) throws AnticipoPagadoError, ReservaFinalizadaError, PagosYaRealizadosError, ReservaRechazadaError;

    public abstract void cancelarReserva(Date fechaCancelacion) throws ReservaFinalizadaError, CancelarError, ReservaRechazadaError;


    public abstract void confirmarReserva() throws PagoNoRealizadoError, ReservaConfirmadaError, ReservaFinalizadaError, ReservaRechazadaError;

    public abstract void rechazarReserva() throws PagoNoRealizadoError, ReservaFinalizadaError, ReservaConfirmadaError, ReservaRechazadaError;

    public abstract ReservaStateEnum getState();



}
