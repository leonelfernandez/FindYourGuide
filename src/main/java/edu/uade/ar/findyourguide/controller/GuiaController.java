package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.*;
import edu.uade.ar.findyourguide.model.dto.GuiaDTO;
import edu.uade.ar.findyourguide.model.entity.*;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.enums.TipoNotificacionEnum;
import edu.uade.ar.findyourguide.model.strategy.impl.PushNotificationStrategyImpl;
import edu.uade.ar.findyourguide.service.*;
import edu.uade.ar.findyourguide.service.IGuiaService;
import edu.uade.ar.findyourguide.util.GuiaMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class GuiaController {

    private IGuiaService guiaService;
    private IVerificacionService verificacionService;
    private Mapper<GuiaEntity, GuiaDTO> guiaMapper;
    private INotificacionService notificacionService;
    private IReseniaService reseniaService;
    private IReservaService reservaService;
    private Mapper<ReservaEntity, ReservaDTO> reservaMapper;
    private Mapper<ReseniaEntity, ReseniaDTO> reseniaMapper;


    public GuiaController(IGuiaService guiaService, IVerificacionService verificacionService, Mapper<GuiaEntity, GuiaDTO> guiaMapper, INotificacionService notificacionService, IReseniaService reseniaService, IReservaService reservaService, Mapper<ReservaEntity, ReservaDTO> reservaMapper, Mapper<ReseniaEntity, ReseniaDTO> reseniaMapper) {
        this.guiaService = guiaService;
        this.verificacionService = verificacionService;
        this.guiaMapper = guiaMapper;
        this.notificacionService = notificacionService;
        this.reseniaService = reseniaService;
        this.reservaService = reservaService;
        this.reservaMapper = reservaMapper;
        this.reseniaMapper = reseniaMapper;
    }

    @PostMapping(path = "/guias")
    public ResponseEntity<NotifGuiaCreadoDTO> crearGuia(@RequestBody GuiaDTO guiaDto) {
        GuiaEntity guia = guiaMapper.mapFrom(guiaDto);
        if (verificacionService.verificarCredencialGuia(guia)) {
            notificacionService.enviarNotificacion(guia, GuiaMessages.credencialValidada(), TipoNotificacionEnum.PUSH_NOTIFICATION);
        }
        GuiaEntity guiaEntityGuardado = guiaService.save(guia);
        return new ResponseEntity<>(new NotifGuiaCreadoDTO(GuiaMessages.credencialValidada(), guiaMapper.mapTo(guiaEntityGuardado)), HttpStatus.CREATED);
    }

    @GetMapping(path = "/guias")
    public List<GuiaDTO> listarGuias() {
        List<GuiaEntity> guias = guiaService.findAll();
        return guias.stream()
                .map(guiaMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/guias/{id}")
    public ResponseEntity<GuiaCompletoDTO> getGuia(@PathVariable("id") Long id) {
        try {
            GuiaEntity foundGuia = guiaService.findById(id).orElseThrow(() -> new RuntimeException("Guia no encontrado"));
            List<ReseniaDTO> resenias = StreamSupport.stream(reseniaService.getReseniasByGuia(id).spliterator(), false)
                    .toList().stream().map(re -> reseniaMapper.mapTo(re)).toList();
            List<Long> ciudadesViajadasId = StreamSupport.stream(reservaService.getReservasFinalizadasByGuia(id).spliterator(), false)
                    .toList().stream().map(res -> res.getCiudad().getId()).toList();
            List<CiudadEntity> ciudadesViajadas = StreamSupport.stream(reservaService.getAllCiudadesIn(ciudadesViajadasId).spliterator(), false).toList();
            List<CiudadDTO> ciudadesViajadasDTO = ciudadesViajadas.stream().map(c -> new CiudadDTO(c.getId(), c.getNombre(), c.getPais())).toList();
            GuiaCompletoDTO guiaDTO = new GuiaCompletoDTO(guiaMapper.mapTo(foundGuia), resenias, new ViajesRealizadosDTO(ciudadesViajadasDTO));
            return new ResponseEntity<>(guiaDTO, HttpStatus.OK);
        } catch(RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

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

    @GetMapping(path = "/guias/viajes/{id}")
    public ResponseEntity<ViajesRealizadosDTO> getGuiasByViaje(@PathVariable("id") Long id) {
        GuiaEntity guia = guiaService.findById(id).orElseThrow(() -> new RuntimeException("Guia no encontrado"));
        List<CiudadEntity> viajesRealizados = guiaService.findViajesRealizados(guia);
        List<CiudadDTO> ciudadesDTO = viajesRealizados.stream().map(c -> new CiudadDTO(c.getId(), c.getNombre(), c.getPais())).toList();
        return new ResponseEntity<>(new ViajesRealizadosDTO(ciudadesDTO),HttpStatus.OK);
    }


}
