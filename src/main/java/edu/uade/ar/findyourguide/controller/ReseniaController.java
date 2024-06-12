package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.ReseniaDTO;
import edu.uade.ar.findyourguide.model.entity.ReseniaEntity;
import edu.uade.ar.findyourguide.service.IReseniaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ReseniaController {
    private IReseniaService reseniaService;
    private Mapper<ReseniaEntity, ReseniaDTO> reseniaMapper;


    @PostMapping(path = "/resenias")
    public ResponseEntity<ReseniaDTO> crearResenia(@RequestBody ReseniaDTO reseniaDTO) {
        ReseniaEntity resenia = reseniaMapper.mapFrom(reseniaDTO);
        ReseniaEntity reseniaEntityGuardado = reseniaService.save(resenia);
        return new ResponseEntity<>(reseniaMapper.mapTo(reseniaEntityGuardado), HttpStatus.CREATED);
    }
    @GetMapping(path = "/resenias")
    public List<ReseniaDTO> listarResenias() {
        List<ReseniaEntity> resenias = reseniaService.findAll();
        return resenias.stream()
                .map(reseniaMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/resenias/{id}")
    public ResponseEntity<ReseniaDTO> getResenia(@PathVariable("id") Long id) {
        Optional<ReseniaEntity> foundResenia = reseniaService.findById(id);
        return foundResenia.map(reseniaEntity -> {
            ReseniaDTO reseniaDTO = reseniaMapper.mapTo(reseniaEntity);
            return new ResponseEntity<>(reseniaDTO, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/resenias/{id}")
    public ResponseEntity<ReseniaDTO> fullUpdateResenia(
            @PathVariable("id") Long id,
            @RequestBody ReseniaDTO reseniaDTO) {

        if(!reseniaService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reseniaDTO.setId(id);
        ReseniaEntity reseniaEntity = reseniaMapper.mapFrom(reseniaDTO);
        ReseniaEntity savedReseniaEntity = reseniaService.save(reseniaEntity);
        return new ResponseEntity<>(
                reseniaMapper.mapTo(savedReseniaEntity),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/resenias/{id}")
    public ResponseEntity<ReseniaDTO> partialUpdateResenia(
            @PathVariable("id") Long id,
            @RequestBody ReseniaDTO reseniaDTO
    ) {
        if(!reseniaService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ReseniaEntity reseniaEntity = reseniaMapper.mapFrom(reseniaDTO);
        ReseniaEntity updatedResenia = reseniaService.partialUpdate(id, reseniaEntity);
        return new ResponseEntity<>(
                reseniaMapper.mapTo(updatedResenia),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/resenias/{id}")
    public ResponseEntity deleteResenia(@PathVariable("id") Long id) {
        reseniaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
