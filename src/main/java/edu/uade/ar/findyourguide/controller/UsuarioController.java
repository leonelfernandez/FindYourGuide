package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.UsuarioDTO;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import edu.uade.ar.findyourguide.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UsuarioController {

    private IUsuarioService usuarioService;
    private Mapper<UsuarioEntity, UsuarioDTO> usuarioMapper;

    public UsuarioController(IUsuarioService usuarioService, Mapper<UsuarioEntity, UsuarioDTO> usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @PostMapping(path = "/usuarios")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDto) {
        UsuarioEntity usuario = usuarioMapper.mapFrom(usuarioDto);
        UsuarioEntity usuarioEntityGuardado = usuarioService.save(usuario);
        return new ResponseEntity<>(usuarioMapper.mapTo(usuarioEntityGuardado), HttpStatus.CREATED);
    }

    @GetMapping(path = "/usuarios")
    public List<UsuarioDTO> listarUsuarios() {
        List<UsuarioEntity> usuarios = usuarioService.findAll();
        return usuarios.stream()
                .map(usuarioMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable("id") Long id) {
        Optional<UsuarioEntity> foundUsuario = usuarioService.findById(id);
        return foundUsuario.map(usuarioEntity -> {
            UsuarioDTO usuarioDTO = usuarioMapper.mapTo(usuarioEntity);
            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> fullUpdateUsuario(
            @PathVariable("id") Long id,
            @RequestBody UsuarioDTO usuarioDTO) {

        if(!usuarioService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        usuarioDTO.setId(id);
        UsuarioEntity usuarioEntity = usuarioMapper.mapFrom(usuarioDTO);
        UsuarioEntity savedUsuarioEntity = usuarioService.save(usuarioEntity);
        return new ResponseEntity<>(
                usuarioMapper.mapTo(savedUsuarioEntity),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> partialUpdateUsuario(
            @PathVariable("id") Long id,
            @RequestBody UsuarioDTO usuarioDTO
    ) {
        if(!usuarioService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UsuarioEntity usuarioEntity = usuarioMapper.mapFrom(usuarioDTO);
        UsuarioEntity updatedUsuario = usuarioService.partialUpdate(id, usuarioEntity);
        return new ResponseEntity<>(
                usuarioMapper.mapTo(updatedUsuario),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/usuarios/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id) {
        usuarioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }






}
