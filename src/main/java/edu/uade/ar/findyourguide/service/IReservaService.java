package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.exceptions.*;
import edu.uade.ar.findyourguide.model.entity.CiudadEntity;
import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReintegroEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IReservaService {
    public List<ReservaEntity> findAll();

    public Optional<ReservaEntity> findById(Long id);

    public ReservaEntity save(ReservaEntity reserva) throws ReservaError;

    public ReservaEntity partialUpdate(Long reservaId, ReservaEntity reserva);

    public void deleteById(Long id);

    public boolean isExists(Long id);


    public ReservaEntity cancelarReserva(ReservaEntity reserva, Date fechaCancelacion) throws ReservaFinalizadaError, ReservaRechazadaError, CancelarError;

    public ReservaEntity rechazarReserva(ReservaEntity reserva, Date fechaCancelacion) throws PagoNoRealizadoError, ReservaConfirmadaError, ReservaFinalizadaError, ReservaRechazadaError;

    public Float calcularMontoTotal(ReservaEntity reserva);

    public Float calcularMontoRestante(ReservaEntity reserva);

    public Float calcularMontoAnticipo(ReservaEntity reserva);

    public ReservaEntity pagar(PagoEntity pago) throws PagosYaRealizadosError, AnticipoPagadoError, ReservaFinalizadaError, ReservaRechazadaError;

    public ReservaEntity confirmarReserva(ReservaEntity reserva) throws PagoNoRealizadoError, ReservaConfirmadaError, ReservaFinalizadaError, ReservaRechazadaError;

    public void realizarReintegro(PagoEntity pago, Date fechaCancelacion);

    public Iterable<ReservaEntity> getReservasFinalizadasByGuia(Long id);

    public Iterable<CiudadEntity> getAllCiudadesIn(List<Long> ids);
}
