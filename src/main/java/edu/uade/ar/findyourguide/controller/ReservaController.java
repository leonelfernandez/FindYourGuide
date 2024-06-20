package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.exceptions.*;
import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.CancelacionDateDTO;
import edu.uade.ar.findyourguide.model.dto.MontoAPagarReservaDTO;
import edu.uade.ar.findyourguide.model.dto.PagoDTO;
import edu.uade.ar.findyourguide.model.dto.ReservaDTO;
import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.service.IGuiaService;
import edu.uade.ar.findyourguide.service.IPagoService;
import edu.uade.ar.findyourguide.service.IReservaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IGuiaService guiaService;
    private Mapper<ReservaEntity, ReservaDTO> reservaMapper;
    private Mapper<PagoEntity, PagoDTO> pagoMapper;


    public ReservaController(IReservaService reservaService, Mapper<ReservaEntity, ReservaDTO> reservaMapper, IPagoService pagoService, IGuiaService guiaService, Mapper<PagoEntity, PagoDTO> pagoMapper) {
        this.reservaService = reservaService;
        this.reservaMapper = reservaMapper;
        this.pagoService = pagoService;
        this.guiaService = guiaService;
        this.pagoMapper = pagoMapper;
    }

    @PostMapping(path = "/reservas")
    public ResponseEntity<ReservaDTO> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        try {
            ReservaEntity reserva = reservaMapper.mapFrom(reservaDTO);
            ReservaEntity reservaEntityGuardado = reservaService.save(reserva);

            return new ResponseEntity<>(reservaMapper.mapTo(reservaEntityGuardado), HttpStatus.CREATED);
        } catch (ReservaError e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); //Falta DTO
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

    @PatchMapping(path = "/reservas/{id}/cancelar")
    public ResponseEntity<ReservaDTO> cancelarReserva(@PathVariable("id") Long id,
                                                      @RequestBody CancelacionDateDTO cancelacionDateDTO
    ) {
        try {
            ReservaEntity reserva = reservaService.findById(id).orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));
            Date fechaCancelacion = cancelacionDateDTO.getFechaCancelacion();
            ReservaEntity reservaCancelada = reservaService.cancelarReserva(reserva, fechaCancelacion);
            //notifacionService.enviarNotificacion(turista, mensaje);//Me altera el estado de la reserva
            return new ResponseEntity<>(reservaMapper.mapTo(reservaCancelada),
                    HttpStatus.OK);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ReservaFinalizadaError e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PatchMapping(path = "/reservas/{id}/rechazar")
    public ResponseEntity<ReservaDTO> rechazarReserva(@PathVariable("id") Long id,
                                                      @RequestBody CancelacionDateDTO cancelacionDateDTO) {
        try {
            ReservaEntity reserva = reservaService.findById(id).orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));
            Date fechaCancelacion = cancelacionDateDTO.getFechaCancelacion();
            ReservaEntity reservaRechazada = reservaService.rechazarReserva(reserva, fechaCancelacion);
            //notifacionService.enviarNotificacion(turista, mensaje);//Me altera el estado de la reserva
            return new ResponseEntity<>(reservaMapper.mapTo(reservaRechazada),
                    HttpStatus.OK);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/reservas/monto/{id}")
    public ResponseEntity<MontoAPagarReservaDTO> getMontoAPagar(@PathVariable("id") Long id) {
        try {
            ReservaEntity reserva = reservaService.findById(id).orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));
            Float montoTotal = reservaService.calcularMontoTotal(reserva);
            Float montoAnticipo = reservaService.calcularMontoAnticipo(reserva);
            Float montoRestante = reservaService.calcularMontoRestante(reserva);
            return new ResponseEntity<>(new MontoAPagarReservaDTO(reserva.getId(), montoAnticipo, montoRestante, montoTotal), HttpStatus.OK);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/pagos")
    public ResponseEntity<ReservaDTO> pagarReserva(@RequestBody PagoDTO pagoDTO) {
        try {
            PagoEntity pago = pagoMapper.mapFrom(pagoDTO);
            PagoEntity pagoGuardado = pagoService.save(pago); //Guardo el pago
            ReservaEntity reservaGuardada = reservaService.pagar(pagoGuardado);
            //notificacionService.enviarNotificacion(reserva.getGuia(), "mensaje");
            return new ResponseEntity<>(reservaMapper.mapTo(reservaGuardada), HttpStatus.CREATED);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (PagosYaRealizadosError | AnticipoPagadoError e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); //Faltan devolver los DTOS de pago no procesado
        } catch (ReservaFinalizadaError e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (PagoNoRealizadoError e) {
            throw new RuntimeException(e);
        } catch (ReservaRechazadaError e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping(path = "/reservas/confirmar/{id}")
    public ResponseEntity<ReservaDTO> confirmarReserva(@PathVariable("id") Long id) {
        try {
            ReservaEntity reserva = reservaService.findById(id).orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));
            ReservaEntity reservaGuardada = reservaService.confirmarReserva(reserva);
            return new ResponseEntity<>(reservaMapper.mapTo(reservaGuardada), HttpStatus.OK);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (PagoNoRealizadoError e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); //Faltan devolver DTOS avisando de los errores
        } catch (ReservaConfirmadaError e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (ReservaFinalizadaError e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (ReservaRechazadaError e) {
            throw new RuntimeException(e);
        }
    }




}
