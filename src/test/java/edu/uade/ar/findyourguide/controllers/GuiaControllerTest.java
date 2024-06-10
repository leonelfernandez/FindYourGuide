package edu.uade.ar.findyourguide.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uade.ar.findyourguide.TestDataUtil;
import edu.uade.ar.findyourguide.model.dto.GuiaDTO;
import edu.uade.ar.findyourguide.model.entity.CiudadEntity;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.PaisEntity;
import edu.uade.ar.findyourguide.model.entity.ServicioEntity;
import edu.uade.ar.findyourguide.service.IGuiaService;
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
public class GuiaControllerTest {

    private IGuiaService guiaService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public GuiaControllerTest(MockMvc mockMvc, IGuiaService guiaService) {
        this.mockMvc = mockMvc;
        this.guiaService = guiaService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void CrearGuiaExitosamenteDevuelve201Test() throws Exception {
        GuiaEntity testAuthorA = TestDataUtil.createTestGuiaEntityA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/guias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void CrearUsuarioExitosamenteDevuelveElUsuarioGuardadoTest() throws Exception {
        GuiaDTO testAuthorA = TestDataUtil.createTestGuiaDTOA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/guias")
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
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.puntajePromedio").value(4.5f)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.credencial").value("Credencial")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.serviciosOfrecidos[0].id").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.serviciosOfrecidos[0].nombre").value("Tour Grupal")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.ciudades[0].id").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.ciudades[0].nombre").value("Buenos Aires")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.ciudades[0].pais.id").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.ciudades[0].pais.nombre").value("Argentina")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idiomas[0].id").value(1L)

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idiomas[0].nombre").value("Espaniol")
        );
    }

    @Test
    public void ListGuiasDevuelve200Test() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/guias")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ListGuiasDevuelveListaDeAutoresTest() throws Exception {
        //GuiaEntity GuiaEntityA = TestDataUtil.createTestGuiaEntityA();
        GuiaEntity savedGuia = guiaService.findById(96L).get();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/guias")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").value(savedGuia.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].nombre").value(savedGuia.getNombre())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].apellido").value(savedGuia.getApellido())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].sexo").value(savedGuia.getSexo())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].dni").value(savedGuia.getDni())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].email").value(savedGuia.getEmail())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].password").value(savedGuia.getPassword())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].telefono").value(savedGuia.getTelefono())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].foto").value(savedGuia.getFoto())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].puntajePromedio").value(savedGuia.getPuntajePromedio())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].credencial").value(savedGuia.getCredencial())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].serviciosOfrecidos[0].id").value(savedGuia.getServiciosOfrecidos().get(0).getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].ciudades[0].id").value(savedGuia.getCiudades().get(0).getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].idiomas[0].id").value(savedGuia.getIdiomas().get(0).getId())
        );
    }

    @Test
    public void getGuiaDevuelve200CuandoExisteTest() throws Exception {
        //GuiaEntity GuiaEntityA = TestDataUtil.createTestGuiaEntityA();
        //guiaService.save(GuiaEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/guias/97")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getGuiaDevuelveElGuiaCuandoEsteExisteTest() throws Exception {
        GuiaEntity GuiaEntityA = TestDataUtil.createTestGuiaEntityA();
        guiaService.save(GuiaEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/guias/97")
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
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.puntajePromedio").value(4.5f)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.credencial").value("Credencial")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.serviciosOfrecidos[0].id").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.serviciosOfrecidos[0].nombre").value("Tour Grupal")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.ciudades[0].id").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.ciudades[0].nombre").value("Buenos Aires")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.ciudades[0].pais.id").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.ciudades[0].pais.nombre").value("Argentina")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idiomas[0].id").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idiomas[0].nombre").value("Espaniol")
        );
    }

    @Test
    public void GetGuiaDevuelve404CuandoNoExisteTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/guias/150")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void FullUpdateGuiaDevuelve404CuandoNoExisteGuiaTest() throws Exception {
        GuiaDTO testGuiaDTOA = TestDataUtil.createTestGuiaDTOA();
        String authorDtoJson = objectMapper.writeValueAsString(testGuiaDTOA);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/guias/150")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void FullUpdateGuiaDevuelve402CuandoExisteGuiaTest() throws Exception {
        GuiaEntity GuiaEntityA = TestDataUtil.createTestGuiaEntityA();
        GuiaEntity usuarioGuardado = guiaService.findById(97L).get();

        GuiaDTO testGuiaDTOA = TestDataUtil.createTestGuiaDTOA();
        String authorDtoJson = objectMapper.writeValueAsString(testGuiaDTOA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/guias/" + usuarioGuardado.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
        GuiaEntity GuiaEntityA = TestDataUtil.createTestGuiaEntityA();
        GuiaEntity savedGuia = guiaService.save(GuiaEntityA);

        GuiaEntity guiaDto = TestDataUtil.createTestGuiaEntityB();
        guiaDto.setId(savedGuia.getId());

        String guiaDtoUpdateJson = objectMapper.writeValueAsString(guiaDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/guias/" + savedGuia.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(guiaDtoUpdateJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedGuia.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.nombre").value(guiaDto.getNombre())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.apellido").value(guiaDto.getApellido())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.sexo").value(guiaDto.getSexo())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.dni").value(guiaDto.getDni())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value(guiaDto.getEmail())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.password").value(guiaDto.getPassword())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.telefono").value(guiaDto.getTelefono())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.foto").value(guiaDto.getFoto())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.puntajePromedio").value(guiaDto.getPuntajePromedio())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.credencial").value(guiaDto.getCredencial())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.serviciosOfrecidos[0].id").value(guiaDto.getServiciosOfrecidos().get(0).getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.ciudades[0].id").value(guiaDto.getCiudades().get(0).getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idiomas[0].id").value(guiaDto.getIdiomas().get(0).getId())
        );
    }

    @Test
    public void PartialUpdateActualizaGuiaDevuelve200Test() throws Exception {
        GuiaEntity GuiaEntityA = TestDataUtil.createTestGuiaEntityA();
        GuiaEntity guiaGuardado = guiaService.findById(1L).get();

        GuiaDTO testGuiaDTOA = TestDataUtil.createTestGuiaDTOA();
        testGuiaDTOA.setNombre("ACTUALIZADO");
        String guiaDtoJson = objectMapper.writeValueAsString(testGuiaDTOA);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/guias/" + guiaGuardado.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(guiaDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void PartialUpdateDevuelveElGuiaActualizadoTest() throws Exception {
        GuiaEntity GuiaEntityA = TestDataUtil.createTestGuiaEntityA();
        GuiaEntity guiaGuardado = guiaService.findById(1L).get();

        GuiaDTO testGuiaDTOA = TestDataUtil.createTestGuiaDTOA();
        testGuiaDTOA.setNombre("ACTUALIZADO");
        String authorDtoJson = objectMapper.writeValueAsString(testGuiaDTOA);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/guias/" + guiaGuardado.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(guiaGuardado.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.nombre").value("ACTUALIZADO")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.apellido").value(testGuiaDTOA.getApellido())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.sexo").value(testGuiaDTOA.getSexo())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.dni").value(testGuiaDTOA.getDni())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value(testGuiaDTOA.getEmail())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.password").value(testGuiaDTOA.getPassword())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.telefono").value(testGuiaDTOA.getTelefono())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.foto").value(testGuiaDTOA.getFoto())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.puntajePromedio").value(testGuiaDTOA.getPuntajePromedio())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.credencial").value(testGuiaDTOA.getCredencial())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.serviciosOfrecidos").value(testGuiaDTOA.getServiciosOfrecidos())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.ciudades").value(testGuiaDTOA.getCiudades())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idiomas").value(testGuiaDTOA.getIdiomas())
        );
    }

    @Test
    public void DeleteGuiaDevuelve204ParaGuiasInexistentesTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/guias/999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void DeleteGuiaDevuelve204ParaGuiasExistentes() throws Exception {
        GuiaEntity GuiaEntityA = TestDataUtil.createTestGuiaEntityA();
        GuiaEntity savedAuthor = guiaService.findById(1L).get();

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/guias/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }


}
