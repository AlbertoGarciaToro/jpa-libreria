package mx.fes.unam.service.libro;

import mx.fes.unam.model.entities.libro.LibroDTO;
import mx.fes.unam.model.entities.libro.LibroEntity;
import org.springframework.stereotype.Component;

@Component
public class LibroMapper {
    //DTO->Entity
    public LibroEntity toEntity(LibroDTO dto){
        LibroEntity libro=new LibroEntity();
        if(dto!=null){
            libro.setId(dto.getId());
            libro.setTitulo(dto.getTitulo());
            libro.setAutor(dto.getAutor());
            libro.setIsbn(dto.getIsbn());
            libro.setPrecio(dto.getPrecio());
            libro.setStock(dto.getStock());
        }
        return libro;
    }
    //Entity->DTO
    public LibroDTO toDTO(LibroEntity libro){
        LibroDTO dto=null;
        if(libro!=null){
            dto=new LibroDTO(
                    libro.getId(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getIsbn(),
                    libro.getPrecio(),
                    libro.getStock()
            );
        }

        return dto;
    }
}
