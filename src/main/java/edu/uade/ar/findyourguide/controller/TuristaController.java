package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.CiudadDTO;
import edu.uade.ar.findyourguide.model.dto.TuristaDTO;
import edu.uade.ar.findyourguide.model.dto.ViajesRealizadosDTO;
import edu.uade.ar.findyourguide.model.entity.CiudadEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.service.ITuristaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TuristaController {

    private ITuristaService turistaService;
    private Mapper<TuristaEntity, TuristaDTO> turistaMapper;

    public TuristaController(ITuristaService turistaService, Mapper<TuristaEntity, TuristaDTO> turistaMapper) {
        this.turistaService = turistaService;
        this.turistaMapper = turistaMapper;
    }

    @PostMapping(path = "/turistas")
    public ResponseEntity<TuristaDTO> crearTurista(@RequestBody TuristaDTO turistaDTO) {
        TuristaEntity turista = turistaMapper.mapFrom(turistaDTO);
        TuristaEntity turistaEntityGuardado = turistaService.save(turista);
        return new ResponseEntity<>(turistaMapper.mapTo(turistaEntityGuardado), HttpStatus.CREATED);
    }

    @GetMapping(path = "/turistas")
    public List<TuristaDTO> listarTuristas() {
        List<TuristaEntity> turistas = turistaService.findAll();
        return turistas.stream()
                .map(turistaMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/turistas/{id}")
    public ResponseEntity<TuristaDTO> getTurista(@PathVariable("id") Long id) {
        Optional<TuristaEntity> foundTurista = turistaService.findById(id);
        return foundTurista.map(turistaEntity -> {
            TuristaDTO turistaDTO = turistaMapper.mapTo(turistaEntity);
            return new ResponseEntity<>(turistaDTO, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/turistas/{id}")
    public ResponseEntity<TuristaDTO> fullUpdateTurista(
            @PathVariable("id") Long id,
            @RequestBody TuristaDTO turistaDTO) {

        if(!turistaService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        turistaDTO.setId(id);
        TuristaEntity turistaEntity = turistaMapper.mapFrom(turistaDTO);
        TuristaEntity savedTuristaEntity = turistaService.save(turistaEntity);
        return new ResponseEntity<>(
                turistaMapper.mapTo(savedTuristaEntity),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/turistas/{id}")
    public ResponseEntity<TuristaDTO> partialUpdateTurista(
            @PathVariable("id") Long id,
            @RequestBody TuristaDTO turistaDTO
    ) {
        if(!turistaService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TuristaEntity turistaEntity = turistaMapper.mapFrom(turistaDTO);
        TuristaEntity updatedTurista = turistaService.partialUpdate(id, turistaEntity);
        return new ResponseEntity<>(
                turistaMapper.mapTo(updatedTurista),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/turistas/{id}")
    public ResponseEntity deleteTurista(@PathVariable("id") Long id) {
        turistaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(path = "/turistas/viajes/{id}")
    public ResponseEntity<ViajesRealizadosDTO> getTuristasByViaje(@PathVariable("id") Long id) {
        TuristaEntity turista = turistaService.findById(id).orElseThrow(() -> new RuntimeException("Turista no encontrado"));
        List<CiudadEntity> viajesRealizados = turistaService.findViajesRealizados(turista);
        List<CiudadDTO> ciudadesDTO = viajesRealizados.stream().map(c -> new CiudadDTO(c.getId(), c.getNombre(), c.getPais())).toList();
        return new ResponseEntity<>(new ViajesRealizadosDTO(ciudadesDTO),HttpStatus.OK);
    }



}
