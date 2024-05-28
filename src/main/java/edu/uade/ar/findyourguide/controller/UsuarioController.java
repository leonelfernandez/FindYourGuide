package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.model.entity.Usuario;
import edu.uade.ar.findyourguide.model.entity.dto.UsuarioDTO;
import edu.uade.ar.findyourguide.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    private IUsuarioService usuarioService;

    // @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    @GetMapping("/usuarios")
    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioDTO usuarioDTO = convertToDTO(usuario);
            usuarioDTOS.add(usuarioDTO);
        }

        return usuarioDTOS;
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<?> getCliente(@PathVariable int clienteId) {
        Usuario usuario = usuarioService.findById(clienteId);

        if (usuario == null) {
            String mensaje = "Cliente no encontrado con ID: " + clienteId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        UsuarioDTO usuarioDTO = convertToDTO(usuario);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }
    @GetMapping("/usuariosParam")
    public ResponseEntity<?> getClienteParam(@RequestParam("clienteId") int clienteId) {
        Usuario usuario = usuarioService.findById(clienteId);

        if (usuario == null) {
            String mensaje = "Cliente no encontrado con ID: " + clienteId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        UsuarioDTO usuarioDTO = convertToDTO(usuario);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioDTO> addCliente(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = convertToEntity(usuarioDTO);

        usuarioService.save(usuario);

        UsuarioDTO nuevoUsuarioDTO = convertToDTO(usuario);

        return new ResponseEntity<>(nuevoUsuarioDTO, HttpStatus.CREATED);
    }

    @PutMapping("/usuarios/{usuarioId}")
    public ResponseEntity<?> updateCliente(@PathVariable int clienteId, @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioOld = usuarioService.findById(clienteId);

        if (usuarioOld == null) {
            String mensaje = "Cliente no encontrado con ID: " + clienteId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        Usuario usuarioToUpdate = convertToEntity(usuarioDTO);
        usuarioService.update(clienteId, usuarioToUpdate);

        UsuarioDTO clienteUpdatedDTO = convertToDTO(usuarioToUpdate);
        return new ResponseEntity<>(clienteUpdatedDTO, HttpStatus.OK);
    }

    @DeleteMapping("usuarios/{usuarioId}")
    public ResponseEntity<String> deleteCliente(@PathVariable int clienteId) {
        Usuario usuario = usuarioService.findById(clienteId);

        if (usuario == null) {
            String mensaje = "Cliente no encontrado con ID: " + clienteId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        usuarioService.deleteById(clienteId);

        String mensaje = "Cliente eliminado [clienteID: " + clienteId + "]";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    /**
     * Método auxiliar para convertir a ClienteDTO
     * @param usuario
     * @return
     */
//    private UsuarioDTO convertToDTO(Usuario usuario) {
//        UsuarioDTO usuarioDTO = new UsuarioDTO();
//        return usuarioDTO;
//    }

    /**
     * Método auxiliar para convertir a Cliente
     * @param usuarioDTO
     * @return
     */
//    private Usuario convertToEntity(UsuarioDTO usuarioDTO) {
//        Usuario usuario = new Usuario();
//        usuario.setNombre(usuarioDTO.getNombre());
//        usuario.setApellido(usuarioDTO.getApellido());
//        usuario.setEmail(usuarioDTO.getEmail());
//        return usuario;
//    }


}
