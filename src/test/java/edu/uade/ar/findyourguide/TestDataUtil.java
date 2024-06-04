package edu.uade.ar.findyourguide;

import edu.uade.ar.findyourguide.model.dto.TuristaDTO;
import edu.uade.ar.findyourguide.model.entity.ServicioEntity;
import edu.uade.ar.findyourguide.model.dto.GuiaDTO;
import edu.uade.ar.findyourguide.model.entity.*;

import java.util.ArrayList;

public final class TestDataUtil {

        private TestDataUtil(){
        }


        public static GuiaEntity createTestGuiaEntityA() {
            ArrayList<CiudadEntity> ciudades = new ArrayList<>();
            ArrayList<ServicioEntity> servicioEntities = new ArrayList<>();
            ArrayList<IdiomaEntity> idiomas = new ArrayList<>();

            ciudades.add(CiudadEntity.builder().id(1L).nombre("Buenos Aires").pais(new PaisEntity(1L)).build());
            servicioEntities.add(ServicioEntity.builder().id(1L).nombre("TourGrupal").build());
            idiomas.add(IdiomaEntity.builder().id(1L).nombre("Espaniol").build());

            return GuiaEntity.builder()
                    .id(1L)
                    .nombre("Leonel")
                    .sexo("Masculino")
                    .apellido("Fernandez")
                    .dni(12345678)
                    .foto("https://www.google.com/foto.jpg")
                    .password("123456")
                    .telefono("152222-3333")
                    .email("pepepaleta@gmail.com")
                    .puntajePromedio(4.5f)
                    .credencial("Credecial")
                    .serviciosOfrecidos(servicioEntities)
                    .ciudades(ciudades)
                    .idiomas(idiomas)
                    .build();
        }

        public static GuiaDTO createTestGuiaDTOA() {
            ArrayList<CiudadEntity> ciudades = new ArrayList<>();
            ArrayList<ServicioEntity> servicioEntities = new ArrayList<>();
            ArrayList<IdiomaEntity> idiomas = new ArrayList<>();

            ciudades.add(CiudadEntity.builder().id(1L).nombre("Buenos Aires").pais(new PaisEntity(1L)).build());
            servicioEntities.add(ServicioEntity.builder().id(1L).nombre("Tour Grupal").build());
            idiomas.add(IdiomaEntity.builder().id(1L).nombre("Espaniol").build());
            return GuiaDTO.builder()
                    .id(1L)
                    .nombre("Leonel")
                    .sexo("Masculino")
                    .apellido("Fernandez")
                    .dni(12345678)
                    .foto("https://www.google.com/foto.jpg")
                    .password("123456")
                    .telefono("152222-3333")
                    .email("pepepaleta@gmail.com")
                    .puntajePromedio(4.5f)
                    .credencial("Credecial")
                    .serviciosOfrecidos(servicioEntities)
                    .ciudades(ciudades)
                    .idiomas(idiomas)
                    .build();
        }

    public static GuiaEntity createTestGuiaEntityB() {
        ArrayList<CiudadEntity> ciudades = new ArrayList<>();
        ArrayList<ServicioEntity> servicioEntities = new ArrayList<>();
        ArrayList<IdiomaEntity> idiomas = new ArrayList<>();

        ciudades.add(CiudadEntity.builder().id(1L).nombre("Buenos Aires").pais(new PaisEntity(1L)).build());
        servicioEntities.add(ServicioEntity.builder().id(1L).nombre("Tour Grupal").build());
        idiomas.add(IdiomaEntity.builder().id(1L).nombre("Espaniol").build());
        return GuiaEntity.builder()
                .id(1L)
                .nombre("Rocio")
                .sexo("Femenino")
                .apellido("Doria")
                .dni(12345678)
                .foto("https://www.google.com/foto.jpg")
                .password("123456")
                .telefono("152222-3333")
                .email("pepepaleta123@gmail.com")
                .puntajePromedio(4.5f)
                .credencial("Credecial")
                .serviciosOfrecidos(servicioEntities)
                .ciudades(ciudades)
                .idiomas(idiomas)
                .build();
    }

    public static GuiaDTO createTestGuiaDTOB() {
        ArrayList<CiudadEntity> ciudades = new ArrayList<>();
        ArrayList<ServicioEntity> servicioEntities = new ArrayList<>();
        ArrayList<IdiomaEntity> idiomas = new ArrayList<>();

        ciudades.add(CiudadEntity.builder().id(1L).nombre("Buenos Aires").pais(new PaisEntity(1L)).build());
        servicioEntities.add(ServicioEntity.builder().id(1L).nombre("Tour Grupal").build());
        idiomas.add(IdiomaEntity.builder().id(1L).nombre("Espaniol").build());
        return GuiaDTO.builder()
                .id(1L)
                .nombre("Rocio")
                .sexo("Femenino")
                .apellido("Doria")
                .dni(12345678)
                .foto("https://www.google.com/foto.jpg")
                .password("123456")
                .telefono("152222-3333")
                .email("pepepaleta123@gmail.com")
                .puntajePromedio(4.5f)
                .credencial("Credecial")
                .serviciosOfrecidos(servicioEntities)
                .ciudades(ciudades)
                .idiomas(idiomas)
                .build();
    }



        public static TuristaEntity createTestTuristaA() {
            return TuristaEntity.builder()
                    .id(2L)
                    .nombre("Juan")
                    .sexo("Masculino")
                    .apellido("Rios")
                    .dni(87654321)
                    .foto("https://www.google.com/foto.jpg")
                    .password("654321")
                    .telefono("152222-4444")
                    .email("juanigrios@gmail.com")
                    .build();
        }

        public static TuristaDTO createTestTuristaDTOA() {
            return TuristaDTO.builder()
                    .id(2L)
                    .nombre("Juan")
                    .sexo("Masculino")
                    .apellido("Rios")
                    .dni(87654321)
                    .foto("https://www.google.com/foto.jpg")
                    .password("654321")
                    .telefono("152222-4444")
                    .email("juanigrios@gmail.com")
                    .build();
        }



}


