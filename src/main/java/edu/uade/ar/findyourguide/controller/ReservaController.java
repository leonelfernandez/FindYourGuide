package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.exceptions.*;
import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.*;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.model.enums.TipoNotificacionEnum;
import edu.uade.ar.findyourguide.service.*;
import edu.uade.ar.findyourguide.util.ReservaMessages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ReservaController {
    private IReservaService reservaService;
    private IPagoService pagoService;
    private Mapper<ReservaEntity, ReservaDTO> reservaMapper;
    private Mapper<PagoEntity, PagoDTO> pagoMapper;
    private IChatService chatService;
    private INotificacionService notificacionService;
    private IGuiaService guiaService;

    public ReservaController(IReservaService reservaService, IPagoService pagoService, Mapper<ReservaEntity, ReservaDTO> reservaMapper, Mapper<PagoEntity, PagoDTO> pagoMapper, IChatService chatService, INotificacionService notificacionService, IGuiaService guiaService) {
        this.reservaService = reservaService;
        this.pagoService = pagoService;
        this.reservaMapper = reservaMapper;
        this.pagoMapper = pagoMapper;
        this.chatService = chatService;
        this.notificacionService = notificacionService;
        this.guiaService = guiaService;
    }

    @PostMapping(path = "/reservas")
    public ResponseEntity<ReservaDTO> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        try {
            ReservaEntity reserva = reservaMapper.mapFrom(reservaDTO);
            ReservaEntity reservaEntityGuardado = reservaService.save(reserva);

            return new ResponseEntity<>(reservaMapper.mapTo(reservaEntityGuardado), HttpStatus.CREATED);
        } catch (ReservaError e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path = "/reservas")
    public List<ReservaDTO> listarReservas() {
        List<ReservaEntity> reservas = reservaService.findAll();
        return reservas.stream()
                .map(reservaMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/reservas/{id}")
    public ResponseEntity<ReservaDTO> getReserva(@PathVariable("id") Long id) {
        Optional<ReservaEntity> foundReserva = reservaService.findById(id);
        return foundReserva.map(reservaEntity -> {
            ReservaDTO reservaDTO = reservaMapper.mapTo(reservaEntity);
            return new ResponseEntity<>(reservaDTO, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/reservas/{id}")
    public ResponseEntity<ReservaDTO> fullUpdateReserva(
            @PathVariable("id") Long id,
            @RequestBody ReservaDTO reservaDTO) {
        try {
            if(!reservaService.isExists(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            reservaDTO.setId(id);
            ReservaEntity reservaEntity = reservaMapper.mapFrom(reservaDTO);
            ReservaEntity savedReservaEntity = reservaService.save(reservaEntity);
            return new ResponseEntity<>(
                    reservaMapper.mapTo(savedReservaEntity),
                    HttpStatus.OK);
        } catch(ReservaError e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PatchMapping(path = "/reservas/{id}")
    public ResponseEntity<ReservaDTO> partialUpdateReserva(
            @PathVariable("id") Long id,
            @RequestBody ReservaDTO reservaDTO
    ) {
        if(!reservaService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ReservaEntity reservaEntity = reservaMapper.mapFrom(reservaDTO);
        ReservaEntity updatedReserva = reservaService.partialUpdate(id, reservaEntity);
        return new ResponseEntity<>(
                reservaMapper.mapTo(updatedReserva),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/reservas/{id}")
    public ResponseEntity deleteReserva(@PathVariable("id") Long id) {
        reservaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = "/reservas/cancelar/{id}")
    public ResponseEntity<ReservaCanceladaDTO> cancelarReserva(@PathVariable("id") Long id,
                                                      @RequestBody CancelacionDateDTO cancelacionDateDTO
    ) {
        try {
            ReservaEntity reserva = reservaService.findById(id).orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));
            Date fechaCancelacion = cancelacionDateDTO.getFechaCancelacion();
            ReservaEntity reservaCancelada = reservaService.cancelarReserva(reserva, fechaCancelacion);
            return new ResponseEntity<>(new ReservaCanceladaDTO(ReservaMessages.reservaCancelada(),reservaMapper.mapTo(reservaCancelada)),
                    HttpStatus.OK);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ReservaFinalizadaError | ReservaRechazadaError | CancelarError e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PatchMapping(path = "/reservas/rechazar/{id}")
    public ResponseEntity<NotifReservaDTO> rechazarReserva(@PathVariable("id") Long id,
                                                           @RequestBody CancelacionDateDTO cancelacionDateDTO) {
        try {
            ReservaEntity reserva = reservaService.findById(id).orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));
            Date fechaCancelacion = cancelacionDateDTO.getFechaCancelacion();
            ReservaEntity reservaRechazada = reservaService.rechazarReserva(reserva, fechaCancelacion);
            notificacionService.enviarNotificacion(reservaRechazada.getTurista(), ReservaMessages.reservaRechazada(reservaRechazada.getTurista()), TipoNotificacionEnum.PUSH_NOTIFICATION);//Me altera el estado de la reserva
            return new ResponseEntity<>(new NotifReservaDTO(ReservaMessages.reservaRechazada(reservaRechazada.getTurista()), reservaMapper.mapTo(reservaRechazada)),
                    HttpStatus.OK);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (PagoNoRealizadoError | ReservaRechazadaError | ReservaConfirmadaError | ReservaFinalizadaError e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path = "/reservas/montos")
    public ResponseEntity<MontoAPagarReservaDTO> simularPrecioReserva(@RequestBody PrecioDeViajeDTO precioViajeDTO) { //Modificar para no persistir la info y poder obtener el monto a pagar
        try {
            Long guiaId = precioViajeDTO.getGuiaId();
            Long ciudadDestinoId = precioViajeDTO.getCiudadDestinoId();
            GuiaEntity guia = guiaService.findById(guiaId).orElseThrow(() -> new EntityNotFoundException("Guia no encontrado"));
            Float montoTotal = reservaService.calcularMontoTotal(guia, ciudadDestinoId);
            Float montoAnticipo = reservaService.calcularMontoAnticipo(guia, ciudadDestinoId);
            Float montoRestante = reservaService.calcularMontoRestante(guia, ciudadDestinoId);
            Float montoComisionPlataforma = reservaService.calcularComisionDePlataforma(guia, ciudadDestinoId);
            return new ResponseEntity<>(new MontoAPagarReservaDTO(ciudadDestinoId, montoAnticipo, montoRestante, montoComisionPlataforma, montoTotal), HttpStatus.OK);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/reservas/pagos")
    public ResponseEntity<ReservaDTO> pagarReserva(@RequestBody PagoDTO pagoDTO) {
        try {
            PagoEntity pago = pagoMapper.mapFrom(pagoDTO);
            PagoEntity pagoGuardado = pagoService.save(pago);//Guardo el pago
            ReservaEntity reservaGuardada = reservaService.pagar(pagoGuardado);
            //notificacionService.enviarNotificacion(reservaGuardada.getGuia(), ReservaMessages.reservaCreadaNotif(reservaGuardada.getTurista()), TipoNotificacionEnum.PUSH_NOTIFICATION);
            return new ResponseEntity<>(reservaMapper.mapTo(reservaGuardada), HttpStatus.CREATED);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (PagosYaRealizadosError | AnticipoPagadoError | ReservaFinalizadaError | ReservaRechazadaError |
                 PagoNoRealizadoError e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PatchMapping(path = "/reservas/confirmar/{id}")
    public ResponseEntity<NotifReservaDTO> confirmarReserva(@PathVariable("id") Long id) {
        try {
            ReservaEntity reserva = reservaService.findById(id).orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));
            ReservaEntity reservaGuardada = reservaService.confirmarReserva(reserva);
            chatService.iniciarChat(reserva.getGuia(), reserva.getTurista());
            notificacionService.enviarNotificacion(reservaGuardada.getTurista(), ReservaMessages.chatIniciado(reservaGuardada.getGuia()), TipoNotificacionEnum.PUSH_NOTIFICATION);
            notificacionService.enviarNotificacion(reservaGuardada.getGuia(), ReservaMessages.chatIniciado(reservaGuardada.getTurista()), TipoNotificacionEnum.PUSH_NOTIFICATION);
            return new ResponseEntity<>(new NotifReservaDTO(ReservaMessages.chatCreado(), reservaMapper.mapTo(reservaGuardada)), HttpStatus.OK);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (PagoNoRealizadoError | ReservaConfirmadaError | ReservaFinalizadaError | ReservaRechazadaError e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PatchMapping(path = "/reservas/finalizar/{id}")
    public ResponseEntity<ReservaDTO> finalizarReserva(@PathVariable("id") Long id) {
        try {
            ReservaEntity reserva = reservaService.findById(id).orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));
            ReservaEntity reservaGuardada = reservaService.finalizarReserva(reserva);
            return new ResponseEntity<>(reservaMapper.mapTo(reservaGuardada), HttpStatus.OK);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (FinalizadoError e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }



}
