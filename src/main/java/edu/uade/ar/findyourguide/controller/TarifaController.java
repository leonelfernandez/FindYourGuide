package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.TarifaDTO;
import edu.uade.ar.findyourguide.model.entity.TarifaEntity;
import edu.uade.ar.findyourguide.model.entity.TarifaEntity;
import edu.uade.ar.findyourguide.service.ITarifaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TarifaController {

    private ITarifaService tarifaService;
    private Mapper<TarifaEntity, TarifaDTO> tarifaMapper;

    public TarifaController(ITarifaService tarifaService, Mapper<TarifaEntity, TarifaDTO> tarifaMapper) {
        this.tarifaService = tarifaService;
        this.tarifaMapper = tarifaMapper;
    }

    @PostMapping(path = "/tarifas")
    public ResponseEntity<TarifaDTO> crearTarifa(@RequestBody TarifaDTO tarifaDTO) {
        TarifaEntity tarifa = tarifaMapper.mapFrom(tarifaDTO);
        TarifaEntity tarifaEntityGuardado = tarifaService.save(tarifa);
        return new ResponseEntity<>(tarifaMapper.mapTo(tarifaEntityGuardado), HttpStatus.CREATED);
    }

    @GetMapping(path = "/tarifas")
    public List<TarifaDTO> listarTarifas() {
        List<TarifaEntity> tarifas = tarifaService.findAll();
        return tarifas.stream()
                .map(tarifaMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/tarifas/{id}")
    public ResponseEntity<TarifaDTO> getTarifa(@PathVariable("id") Long id) {
        Optional<TarifaEntity> foundTarifa = tarifaService.findById(id);
        return foundTarifa.map(tarifaEntity -> {
            TarifaDTO tarifaDTO = tarifaMapper.mapTo(tarifaEntity);
            return new ResponseEntity<>(tarifaDTO, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/tarifas/{id}")
    public ResponseEntity<TarifaDTO> fullUpdateTarifa(
            @PathVariable("id") Long id,
            @RequestBody TarifaDTO tarifaDTO) {

        if(!tarifaService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tarifaDTO.setId(id);
        TarifaEntity tarifaEntity = tarifaMapper.mapFrom(tarifaDTO);
        TarifaEntity savedTarifaEntity = tarifaService.save(tarifaEntity);
        return new ResponseEntity<>(
                tarifaMapper.mapTo(savedTarifaEntity),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/tarifas/{id}")
    public ResponseEntity<TarifaDTO> partialUpdateTarifa(
            @PathVariable("id") Long id,
            @RequestBody TarifaDTO tarifaDTO
    ) {
        if(!tarifaService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TarifaEntity tarifaEntity = tarifaMapper.mapFrom(tarifaDTO);
        TarifaEntity updatedTarifa = tarifaService.partialUpdate(id, tarifaEntity);
        return new ResponseEntity<>(
                tarifaMapper.mapTo(updatedTarifa),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/tarifas/{id}")
    public ResponseEntity deleteTarifa(@PathVariable("id") Long id) {
        tarifaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
