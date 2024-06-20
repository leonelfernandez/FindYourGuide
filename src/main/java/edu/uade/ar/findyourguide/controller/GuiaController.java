package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.*;
import edu.uade.ar.findyourguide.model.dto.GuiaDTO;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.service.IGuiaService;
import edu.uade.ar.findyourguide.service.IGuiaService;
import edu.uade.ar.findyourguide.service.IVerificacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class GuiaController {

    private IGuiaService guiaService;

    private IVerificacionService verificacionService;
    private Mapper<GuiaEntity, GuiaDTO> guiaMapper;

    public GuiaController(IGuiaService guiaService, Mapper<GuiaEntity, GuiaDTO> guiaMapper, IVerificacionService verificacionService) {
        this.guiaService = guiaService;
        this.guiaMapper = guiaMapper;
        this.verificacionService = verificacionService;

    }

    @PostMapping(path = "/guias")
    public ResponseEntity<GuiaDTO> crearGuia(@RequestBody GuiaDTO guiaDto) {
        GuiaEntity guia = guiaMapper.mapFrom(guiaDto);
        if (!verificacionService.verificarCredencialGuia(guia)) //Esto va en el servicio de abril en el registro
            //notificacionService.enviarNotificacion(guia, mensaje);
            return new ResponseEntity<>(guiaMapper.mapTo(guia), HttpStatus.BAD_REQUEST);
        GuiaEntity guiaEntityGuardado = guiaService.save(guia);
        return new ResponseEntity<>(guiaMapper.mapTo(guiaEntityGuardado), HttpStatus.CREATED);
    }

    @GetMapping(path = "/guias")
    public List<GuiaDTO> listarGuias() {
        List<GuiaEntity> guias = guiaService.findAll();
        return guias.stream()
                .map(guiaMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/guias/{id}")
    public ResponseEntity<GuiaDTO> getGuia(@PathVariable("id") Long id) {
        Optional<GuiaEntity> foundGuia = guiaService.findById(id);
        return foundGuia.map(guiaEntity -> {
            GuiaDTO guiaDTO = guiaMapper.mapTo(guiaEntity);
            return new ResponseEntity<>(guiaDTO, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/guias/{id}")
    public ResponseEntity<GuiaDTO> fullUpdateGuia(
            @PathVariable("id") Long id,
            @RequestBody GuiaDTO guiaDTO) {

        if(!guiaService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        guiaDTO.setId(id);
        GuiaEntity guiaEntity = guiaMapper.mapFrom(guiaDTO);
        GuiaEntity savedGuiaEntity = guiaService.save(guiaEntity);
        return new ResponseEntity<>(
                guiaMapper.mapTo(savedGuiaEntity),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/guias/{id}")
    public ResponseEntity<GuiaDTO> partialUpdateGuia(
            @PathVariable("id") Long id,
            @RequestBody GuiaDTO guiaDTO
    ) {
        if(!guiaService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        GuiaEntity guiaEntity = guiaMapper.mapFrom(guiaDTO);
        GuiaEntity updatedGuia = guiaService.partialUpdate(id, guiaEntity);
        return new ResponseEntity<>(
                guiaMapper.mapTo(updatedGuia),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/guias/{id}")
    public ResponseEntity deleteGuia(@PathVariable("id") Long id) {
        guiaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/guias/pais/{id}")
    public List<GuiaDTO> listarGuiasPorPais(@PathVariable("id") Long id) {
        List<GuiaEntity> guias = guiaService.findByPais(id);
        return guias.stream()
                .map(guiaMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/guias/ciudad/{id}")
    public List<GuiaDTO> listarGuiasPorCiudad(@PathVariable("id") Long id) {
        List<GuiaEntity> guias = guiaService.findByCiudad(id);
        return guias.stream()
                .map(guiaMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/guias/nombre/{nombre}")
    public List<GuiaDTO> listarGuiasPorNombre(@PathVariable("nombre") String nombre) {
        List<GuiaEntity> guias = guiaService.findByNombre(nombre);
        return guias.stream()
                .map(guiaMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/guias/apellido/{apellido}")
    public List<GuiaDTO> listarGuiasPorApellido(@PathVariable("apellido") String apellido) {
        List<GuiaEntity> guias = guiaService.findByApellido(apellido);
        return guias.stream()
                .map(guiaMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/guias/puntuaciones")
    public List<GuiaDTO> listarGuiasPorPuntuacionPromedio(@RequestBody GuiaPuntuacionDTO puntuacion) {
        List<GuiaEntity> guias = guiaService.findByPuntuacion(puntuacion.getPuntuacion());
        return guias.stream()
                .map(guiaMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/guias/idiomas")
    public List<GuiaDTO> listarGuiasPorIdioma(@RequestBody IdiomasGuiaDTO idiomas) {
        List<GuiaEntity> guias = guiaService.findByIdiomas(idiomas.getIdIdiomas());
        return guias.stream()
                .map(guiaMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/guias/servicios")
    public List<GuiaDTO> listarGuiasPorServicio(@RequestBody ServicioGuiaDTO servicios) {
        List<GuiaEntity> guias = guiaService.findByServicios(servicios.getIdServicios());
        return guias.stream()
                .map(guiaMapper::mapTo)
                .collect(Collectors.toList());
    }


}
