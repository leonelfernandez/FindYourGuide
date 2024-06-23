package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.*;
import edu.uade.ar.findyourguide.model.dto.ReseniaDTO;
import edu.uade.ar.findyourguide.model.entity.ReseniaEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.model.enums.TipoNotificacionEnum;
import edu.uade.ar.findyourguide.service.*;
import edu.uade.ar.findyourguide.service.IReseniaService;
import edu.uade.ar.findyourguide.service.impl.TuristaServiceImpl;
import edu.uade.ar.findyourguide.util.ReseniaMessages;
import edu.uade.ar.findyourguide.util.TuristaMessages;
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

    private ITuristaService turistaService;

    private INotificacionService notificacionService;

    private IGuiaService guiaService;



    public ReseniaController(IReseniaService reseniaService, Mapper<ReseniaEntity, ReseniaDTO> reseniaMapper, ITuristaService turistaService, INotificacionService notificacionService, IGuiaService guiaService) {
        this.reseniaService = reseniaService;
        this.reseniaMapper = reseniaMapper;
        this.turistaService = turistaService;
        this.notificacionService = notificacionService;
        this.guiaService = guiaService;
    }

    @GetMapping(path = "/resenias/calificar/{id}")
    public ResponseEntity<NotifTuristaReseniaDTO> calificarResenia(@PathVariable("id") Long id) {
        TuristaEntity turista = turistaService.findById(id).orElseThrow(() -> new RuntimeException("Turista no encontrado"));
        if (notificacionService.diaParaenviarResenia(turista)) { //Valido si es el dia para enviar la calificacion
            notificacionService.enviarNotificacion(turista, "Califica tu experiencia con FindYourGuide", TipoNotificacionEnum.PUSH_NOTIFICATION);
            notificacionService.enviarNotificacion(turista, "Califica tu experiencia con FindYourGuide", TipoNotificacionEnum.EMAIL);
        }
        return new ResponseEntity<>(new NotifTuristaReseniaDTO(TuristaMessages.calificaFindYourGuide()), HttpStatus.OK);
    }

    @PostMapping(path = "/resenias")
    public ResponseEntity<NotifReseniaDTO> crearResenia(@RequestBody ReseniaDTO reseniaDTO) {
        ReseniaEntity resenia = reseniaMapper.mapFrom(reseniaDTO);
        ReseniaEntity reseniaEntityGuardado = reseniaService.save(resenia);
        if (guiaService.findByTrofeos(reseniaEntityGuardado.getGuia().getId())) {
            notificacionService.enviarNotificacion(reseniaEntityGuardado.getGuia(), ReseniaMessages.trofeoObtenido(), TipoNotificacionEnum.PUSH_NOTIFICATION);
            return new ResponseEntity<>(new NotifReseniaDTO(ReseniaMessages.trofeoObtenido(), reseniaMapper.mapTo(reseniaEntityGuardado)), HttpStatus.CREATED);
        } else if (turistaService.findByTrofeos(reseniaEntityGuardado.getTurista().getId())) {
            notificacionService.enviarNotificacion(reseniaEntityGuardado.getTurista(), ReseniaMessages.trofeoObtenido(), TipoNotificacionEnum.PUSH_NOTIFICATION);
            return new ResponseEntity<>(new NotifReseniaDTO(ReseniaMessages.trofeoObtenido(), reseniaMapper.mapTo(reseniaEntityGuardado)), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new NotifReseniaDTO(ReseniaMessages.reseniaCreada(), reseniaMapper.mapTo(reseniaEntityGuardado)), HttpStatus.CREATED);
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
