package mx.fes.unam.service;

import mx.fes.unam.model.entities.libro.LibroDTO;
import mx.fes.unam.model.entities.libro.LibroEntity;
import mx.fes.unam.service.libreria.LibroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class LibreriaServiceTest {
    @Autowired
    LibroService libreriaService;

    @Test
    public void PruebaUno(){
        LibroDTO libro= LibroDTO.builder()
                .autor("mike")
                .precio(new BigDecimal(45.34))
                .build();
        libreriaService.guardarLibro(libro);
        System.out.println(libro.getId());
    }

    @Test
    void pruebaDos(){

    }
}