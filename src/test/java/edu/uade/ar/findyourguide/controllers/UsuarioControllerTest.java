package edu.uade.ar.findyourguide.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uade.ar.findyourguide.TestDataUtil;
import edu.uade.ar.findyourguide.model.dto.UsuarioDTO;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import edu.uade.ar.findyourguide.service.IUsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    private IUsuarioService usuarioService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public UsuarioControllerTest(MockMvc mockMvc, IUsuarioService usuarioService) {
        this.mockMvc = mockMvc;
        this.usuarioService = usuarioService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void CrearUsuarioExitosamenteDevuelve201Test() throws Exception {
        UsuarioEntity testAuthorA = TestDataUtil.createTestUsuarioEntityA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void CrearUsuarioExitosamenteDevuelveElUsuarioGuardadoTest() throws Exception {
        UsuarioDTO testAuthorA = TestDataUtil.createTestUsuarioDTOA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.nombre").value("Leonel")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.apellido").value("Fernandez")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.sexo").value("Masculino")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.dni").value(12345678)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value("pepepaleta@gmail.com")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.password").value("123456")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.telefono").value("152222-3333")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.foto").value("https://www.google.com/foto.jpg")
        );
    }

    @Test
    public void ListUsuariosDevuelve200Test() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ListUsuariosDevuelveListaDeAutoresTest() throws Exception {
        UsuarioEntity testUsuarioEntityA = TestDataUtil.createTestUsuarioEntityA();
        usuarioService.save(testUsuarioEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].nombre").value("Leonel")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].apellido").value("Fernandez")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].sexo").value("Masculino")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].dni").value(12345678)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].email").value("pepepaleta@gmail.com")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].password").value("123456")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].telefono").value("152222-3333")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].foto").value("https://www.google.com/foto.jpg")
        );
    }

    @Test
    public void getUsuarioDevuelve200CuandoExisteTest() throws Exception {
        UsuarioEntity testUsuarioEntityA = TestDataUtil.createTestUsuarioEntityA();
        usuarioService.save(testUsuarioEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getUsuarioDevuelveElUsuarioCuandoEsteExisteTest() throws Exception {
        UsuarioEntity testUsuarioEntityA = TestDataUtil.createTestUsuarioEntityA();
        usuarioService.save(testUsuarioEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.nombre").value("Leonel")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.apellido").value("Fernandez")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.sexo").value("Masculino")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.dni").value(12345678)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value("pepepaleta@gmail.com")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.password").value("123456")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.telefono").value("152222-3333")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.foto").value("https://www.google.com/foto.jpg")
        );
    }

    @Test
    public void GetUsuarioDevuelve404CuandoNoExisteTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/usuarios/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void FullUpdateUsuarioDevuelve404CuandoNoExisteUsuarioTest() throws Exception {
        UsuarioDTO testUsuarioDTOA = TestDataUtil.createTestUsuarioDTOA();
        String authorDtoJson = objectMapper.writeValueAsString(testUsuarioDTOA);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/usuarios/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void FullUpdateUsuarioDevuelve402CuandoExisteUsuarioTest() throws Exception {
        UsuarioEntity testUsuarioEntityA = TestDataUtil.createTestUsuarioEntityA();
        UsuarioEntity usuarioGuardado = usuarioService.save(testUsuarioEntityA);

        UsuarioDTO testUsuarioDTOA = TestDataUtil.createTestUsuarioDTOA();
        String authorDtoJson = objectMapper.writeValueAsString(testUsuarioDTOA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/usuarios/" + usuarioGuardado.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void FullUpdateUsuarioActualizaUsuarioExistenteTest() throws Exception {
        UsuarioEntity testUsuarioEntityA = TestDataUtil.createTestUsuarioEntityA();
        UsuarioEntity usuarioGuardado = usuarioService.save(testUsuarioEntityA);

        UsuarioEntity authorDto = TestDataUtil.createTestAuthorB();
        authorDto.setId(usuarioGuardado.getId());

        String authorDtoUpdateJson = objectMapper.writeValueAsString(authorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/usuarios/" + usuarioGuardado.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoUpdateJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(usuarioGuardado.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.nombre").value("Leonel")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.apellido").value("Fernandez")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.sexo").value("Masculino")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.dni").value(12345678)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value("pepepaleta@gmail.com")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.password").value("123456")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.telefono").value("152222-3333")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.foto").value("https://www.google.com/foto.jpg")
        );
    }


    @Test
    public void PartialUpdateActualizaUsuarioDevuelve200Test() throws Exception {
        UsuarioEntity testUsuarioEntityA = TestDataUtil.createTestUsuarioEntityA();
        UsuarioEntity usuarioGuardado = usuarioService.save(testUsuarioEntityA);

        UsuarioDTO testUsuarioDTOA = TestDataUtil.createTestUsuarioDTOA();
        testUsuarioDTOA.setNombre("ACTUALIZADO");
        String authorDtoJson = objectMapper.writeValueAsString(testUsuarioDTOA);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/usuarios/" + usuarioGuardado.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void PartialUpdateDevuelveElUsuarioActualizadoTest() throws Exception {
        UsuarioEntity testUsuarioEntityA = TestDataUtil.createTestUsuarioEntityA();
        UsuarioEntity usuarioGuardado = usuarioService.save(testUsuarioEntityA);

        UsuarioDTO testUsuarioDTOA = TestDataUtil.createTestUsuarioDTOA();
        testUsuarioDTOA.setNombre("ACTUALIZADO");
        String authorDtoJson = objectMapper.writeValueAsString(testUsuarioDTOA);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/usuarios/" + usuarioGuardado.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(usuarioGuardado.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.nombre").value("Leonel")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.apellido").value("Fernandez")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.sexo").value("Masculino")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.dni").value(12345678)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value("pepepaleta@gmail.com")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.password").value("123456")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.telefono").value("152222-3333")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.foto").value("https://www.google.com/foto.jpg")
        );
    }

    @Test
    public void DeleteUsuarioDevuelve204ParaUsuariosInexistentesTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/usuarios/999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void DeleteUsuarioDevuelve204ParaUsuariosExistentes() throws Exception {
        UsuarioEntity testUsuarioEntityA = TestDataUtil.createTestUsuarioEntityA();
        UsuarioEntity savedAuthor = usuarioService.save(testUsuarioEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/usuarios/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }


}
