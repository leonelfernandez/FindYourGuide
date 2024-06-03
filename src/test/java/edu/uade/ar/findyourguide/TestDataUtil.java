package edu.uade.ar.findyourguide;

import edu.uade.ar.findyourguide.model.dto.UsuarioDTO;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;

public final class TestDataUtil {

        private TestDataUtil(){
        }

        public static UsuarioEntity createTestUsuarioEntityA() {
            return UsuarioEntity.builder()
                    .id(1L)
                    .nombre("Leonel")
                    .sexo("Masculino")
                    .apellido("Fernandez")
                    .dni(12345678)
                    .foto("https://www.google.com/foto.jpg")
                    .password("123456")
                    .telefono("152222-3333")
                    .email("pepepaleta@gmail.com")
                    .build();
        }

        public static UsuarioDTO createTestUsuarioDTOA() {
            return UsuarioDTO.builder()
                    .id(1L)
                    .nombre("Leonel")
                    .sexo("Masculino")
                    .apellido("Fernandez")
                    .dni(12345678)
                    .foto("https://www.google.com/foto.jpg")
                    .password("123456")
                    .telefono("152222-3333")
                    .email("pepepaleta@gmail.com")
                    .build();
        }

        public static UsuarioEntity createTestAuthorB() {
            return UsuarioEntity.builder()
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

        public static UsuarioEntity createTestAuthorC() {
            return UsuarioEntity.builder()
                    .id(3L)
                    .nombre("Rocio")
                    .sexo("Femenino")
                    .apellido("Doria")
                    .dni(8123456)
                    .foto("https://www.google.com/foto.jpg")
                    .password("124356")
                    .telefono("152222-5555")
                    .email("rociodoria@gmail.com")
                    .build();
        }

}


