package edu.uade.ar.findyourguide.controller;
import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.GuiaCreadoDTO;
import edu.uade.ar.findyourguide.model.dto.GuiaDTO;
import edu.uade.ar.findyourguide.model.dto.NotifGuiaCreadoDTO;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.enums.TipoNotificacionEnum;
import edu.uade.ar.findyourguide.service.*;
import edu.uade.ar.findyourguide.service.impl.JwtServiceImpl;
import edu.uade.ar.findyourguide.util.GuiaMessages;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationController {

    private IVerificacionService verificacionService;
    private Mapper<GuiaEntity, GuiaDTO> guiaMapper;
    private INotificacionService notificacionService;
    private IAuthenticationService authenticationService;
    private IJwtService jwtService;

    public AuthenticationController(IVerificacionService verificacionService, Mapper<GuiaEntity, GuiaDTO> guiaMapper, INotificacionService notificacionService, IAuthenticationService authenticationService, IJwtService jwtService) {
        this.verificacionService = verificacionService;
        this.guiaMapper = guiaMapper;
        this.notificacionService = notificacionService;
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/api/auth/register/guia")
    public ResponseEntity<GuiaCreadoDTO> crearGuia(@RequestBody GuiaDTO guiaDto) {
        GuiaEntity guia = guiaMapper.mapFrom(guiaDto);
        if (verificacionService.verificarCredencialGuia(guia)) {
            notificacionService.enviarNotificacion(guia, GuiaMessages.credencialValidada(), TipoNotificacionEnum.PUSH_NOTIFICATION);
        }
        GuiaEntity guiaEntityGuardado = authenticationService.registrarGuia(guia);
        String token = jwtService.generateToken(guiaEntityGuardado);
        return new ResponseEntity<>(new GuiaCreadoDTO(GuiaMessages.credencialValidada(), token, guiaMapper.mapTo(guiaEntityGuardado)), HttpStatus.CREATED);
    }
}
