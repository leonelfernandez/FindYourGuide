package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.CancelacionDateDTO;
import edu.uade.ar.findyourguide.model.dto.ReservaDTO;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.service.IPagoService;
import edu.uade.ar.findyourguide.service.IReservaService;
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

    private IPagoService pagoService; //Con esto pago el anticipo y el resto del viaje
    private Mapper<ReservaEntity, ReservaDTO> reservaMapper;

    public ReservaController(IReservaService reservaService, Mapper<ReservaEntity, ReservaDTO> reservaMapper, IPagoService pagoService) {
        this.reservaService = reservaService;
        this.reservaMapper = reservaMapper;
        this.pagoService = pagoService;
    }

    @PostMapping(path = "/reservas")
    public ResponseEntity<ReservaDTO> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        ReservaEntity turista = reservaMapper.mapFrom(reservaDTO);
        ReservaEntity reservaEntityGuardado = reservaService.save(turista);
        return new ResponseEntity<>(reservaMapper.mapTo(reservaEntityGuardado), HttpStatus.CREATED);
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

        if(!reservaService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reservaDTO.setId(id);
        ReservaEntity reservaEntity = reservaMapper.mapFrom(reservaDTO);
        ReservaEntity savedReservaEntity = reservaService.save(reservaEntity);
        return new ResponseEntity<>(
                reservaMapper.mapTo(savedReservaEntity),
                HttpStatus.OK);
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

    @GetMapping(path = "/reservas/{id}/cancelar")
    public ResponseEntity<ReservaDTO> cancelarReserva(@PathVariable("id") Long id,
                                                      @RequestBody CancelacionDateDTO cancelacionDateDTO
    ) {
        ReservaEntity reserva = reservaService.findById(id).orElse(null);
        Date fechaCancelacion = cancelacionDateDTO.getFechaCancelacion();
        if (reserva == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reservaService.cancelarReserva(reserva, fechaCancelacion);
        return new ResponseEntity<>(reservaMapper.mapTo(reserva),
                HttpStatus.OK);
    }

}
