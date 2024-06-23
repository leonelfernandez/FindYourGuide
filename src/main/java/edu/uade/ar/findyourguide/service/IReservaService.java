package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.exceptions.*;
import edu.uade.ar.findyourguide.model.entity.*;

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


    Float calcularMontoTotal(GuiaEntity guia, Long ciudadDestinoId);

    Float calcularComisionDePlataforma(GuiaEntity guia, Long ciudadDestinoId);

    public Float calcularMontoRestante(GuiaEntity guia, Long ciudadDestinoId);

    public Float calcularMontoAnticipo(GuiaEntity guia, Long ciudadDestinoId);

    public ReservaEntity pagar(PagoEntity pago) throws PagosYaRealizadosError, AnticipoPagadoError, ReservaFinalizadaError, ReservaRechazadaError;

    public ReservaEntity confirmarReserva(ReservaEntity reserva) throws PagoNoRealizadoError, ReservaConfirmadaError, ReservaFinalizadaError, ReservaRechazadaError;

    public void realizarReintegro(PagoEntity pago, Date fechaCancelacion);

    public Iterable<ReservaEntity> getReservasFinalizadasByGuia(Long id);

    public Iterable<CiudadEntity> getAllCiudadesIn(List<Long> ids);

    public ReservaEntity finalizarReserva(ReservaEntity reserva) throws FinalizadoError;
}
