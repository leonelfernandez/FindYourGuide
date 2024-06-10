package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.PagoDTO;
import edu.uade.ar.findyourguide.model.dto.PagoDTO;
import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import edu.uade.ar.findyourguide.service.IPagoService;
import edu.uade.ar.findyourguide.service.IReservaService;
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

    public PagoController(IPagoService pagoService, Mapper<PagoEntity, PagoDTO> pagoMapper) {
        this.pagoService = pagoService;
        this.pagoMapper = pagoMapper;
    }

    @PostMapping(path = "/pagos")
    public ResponseEntity<PagoDTO> crearPago(@RequestBody PagoDTO pagoDTO) {
        PagoEntity pago = pagoMapper.mapFrom(pagoDTO);
        PagoEntity pagoEntityGuardado = pagoService.save(pago);
        return new ResponseEntity<>(pagoMapper.mapTo(pagoEntityGuardado), HttpStatus.CREATED);
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

    @PutMapping(path = "/pagos/{id}")
    public ResponseEntity<PagoDTO> fullUpdatePago(
            @PathVariable("id") Long id,
            @RequestBody PagoDTO pagoDTO) {

        if(!pagoService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pagoDTO.setId(id);
        PagoEntity pagoEntity = pagoMapper.mapFrom(pagoDTO);
        PagoEntity savedPagoEntity = pagoService.save(pagoEntity);
        return new ResponseEntity<>(
                pagoMapper.mapTo(savedPagoEntity),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/pagos/{id}")
    public ResponseEntity<PagoDTO> partialUpdatePago(
            @PathVariable("id") Long id,
            @RequestBody PagoDTO pagoDTO
    ) {
        if(!pagoService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PagoEntity pagoEntity = pagoMapper.mapFrom(pagoDTO);
        PagoEntity updatedPago = pagoService.partialUpdate(id, pagoEntity);
        return new ResponseEntity<>(
                pagoMapper.mapTo(updatedPago),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/pagos/{id}")
    public ResponseEntity deletePago(@PathVariable("id") Long id) {
        pagoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
