package edu.uade.ar.findyourguide.controller;
import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.*;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import edu.uade.ar.findyourguide.model.enums.TipoNotificacionEnum;
import edu.uade.ar.findyourguide.model.enums.TipoRegistroEnum;
import edu.uade.ar.findyourguide.service.*;
import edu.uade.ar.findyourguide.util.GuiaMessages;
import edu.uade.ar.findyourguide.util.TuristaMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private IVerificacionService verificacionService;
    private Mapper<GuiaEntity, GuiaDTO> guiaMapper;
    private INotificacionService notificacionService;
    private IAuthenticationService authenticationService;
    private IJwtService jwtService;
    private Mapper<TuristaEntity, TuristaDTO> turistaMapper;


    public AuthController(IVerificacionService verificacionService, Mapper<GuiaEntity, GuiaDTO> guiaMapper, INotificacionService notificacionService, IAuthenticationService authenticationService, IJwtService jwtService, Mapper<TuristaEntity, TuristaDTO> turistaMapper) {
        this.verificacionService = verificacionService;
        this.guiaMapper = guiaMapper;
        this.notificacionService = notificacionService;
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.turistaMapper = turistaMapper;
    }

    @PostMapping(path = "/api/auth/register/guia")
    public ResponseEntity<NotifGuiaCreadoDTO> registerGuia(@RequestBody RegistroGuiaDTO guiaDto) {
        TipoRegistroEnum tipoDeRegistro = guiaDto.getTipoRegistro();
        GuiaEntity guia = guiaMapper.mapFrom(guiaDto.getGuia());
        if (verificacionService.verificarCredencialGuia(guia)) {
            notificacionService.enviarNotificacion(guia, GuiaMessages.credencialValidada(), TipoNotificacionEnum.PUSH_NOTIFICATION);
        }
        GuiaEntity guiaEntityGuardado = authenticationService.registrarGuia(guia, tipoDeRegistro);
        return new ResponseEntity<>(new NotifGuiaCreadoDTO(GuiaMessages.credencialValidada(), guiaMapper.mapTo(guiaEntityGuardado)), HttpStatus.CREATED);
    }

    @PostMapping(path = "/api/auth/register/turista")
    public ResponseEntity<NotifTuristaCreadoDTO> registerTurista(@RequestBody RegistroTuristaDTO turistaDTO) {
        TipoRegistroEnum tipoDeRegistro = turistaDTO.getTipoRegistro();
        TuristaEntity turista = turistaMapper.mapFrom(turistaDTO.getTurista());
        TuristaEntity turistaEntityGuardado = authenticationService.registrarTurista(turista, tipoDeRegistro);
        return new ResponseEntity<>(new NotifTuristaCreadoDTO(TuristaMessages.turistaCreadoConExito(),turistaMapper.mapTo(turistaEntityGuardado)), HttpStatus.CREATED);
    }

    @GetMapping(path = "/api/auth/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        UsuarioEntity usuario = authenticationService.login(usuarioLoginDTO.getEmail(), usuarioLoginDTO.getPassword());
        String token = jwtService.generateToken(usuario);
        return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
    }



}
