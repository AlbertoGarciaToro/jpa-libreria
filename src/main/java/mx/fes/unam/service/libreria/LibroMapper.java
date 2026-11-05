package mx.fes.unam.service.libreria;

import mx.fes.unam.model.entities.libro.LibroDTO;
import mx.fes.unam.model.entities.libro.LibroEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LibroMapper {
    //DTO->Entity
    public LibroEntity toEntity(LibroDTO dto){
        LibroEntity libro=new LibroEntity();
        if(dto!=null){
            libro.setId(dto.getId());
            libro.setAutor(dto.getAutor());
            libro.setPrecio(dto.getPrecio());
        }
        return libro;
    }
    //Entity->DTO
    public LibroDTO toDTO(LibroEntity libro){
        LibroDTO dto=null;
        if(libro!=null){
            dto=new LibroDTO(
                    libro.getId(),
                    libro.getAutor(),
                    libro.getPrecio()
            );
        }

        return dto;
    }
}
