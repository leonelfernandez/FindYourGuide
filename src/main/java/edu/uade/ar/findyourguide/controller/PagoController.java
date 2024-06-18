package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.PagoDTO;
import edu.uade.ar.findyourguide.model.dto.PagoDTO;
import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import edu.uade.ar.findyourguide.service.IPagoService;
import edu.uade.ar.findyourguide.service.IReservaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PagoController {
    private IPagoService pagoService;
    private Mapper<PagoEntity, PagoDTO> pagoMapper;

    private IReservaService reservaService;

    public PagoController(IPagoService pagoService, Mapper<PagoEntity, PagoDTO> pagoMapper, IReservaService reservaService) {
        this.pagoService = pagoService;
        this.pagoMapper = pagoMapper;
        this.reservaService = reservaService;
    }

    @PostMapping(path = "/pagos")
    public ResponseEntity<PagoDTO> crearPago(@RequestBody PagoDTO pagoDTO) { //Seria realizar el pago
        try {
            PagoEntity pago = pagoMapper.mapFrom(pagoDTO);
            PagoEntity pagoEntityGuardado = pagoService.save(pago);
            //notificacionService.enviarNotificacion(reserva.getGuia(), "mensaje");
            return new ResponseEntity<>(pagoMapper.mapTo(pagoEntityGuardado), HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(path = "/pagos")
    public List<PagoDTO> listarPagos() {
        List<PagoEntity> reservas = pagoService.findAll();
        return reservas.stream()
                .map(pagoMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/pagos/{id}")
    public ResponseEntity<PagoDTO> getPago(@PathVariable("id") Long id) {
        Optional<PagoEntity> foundPago = pagoService.findById(id);
        return foundPago.map(pagoEntity -> {
            PagoDTO pagoDTO = pagoMapper.mapTo(pagoEntity);
            return new ResponseEntity<>(pagoDTO, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
