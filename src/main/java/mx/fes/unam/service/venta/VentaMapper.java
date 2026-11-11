package mx.fes.unam.service.venta;

import lombok.RequiredArgsConstructor;
import mx.fes.unam.model.entities.Usuario.UsuarioEntity;
import mx.fes.unam.model.entities.Venta.VentaDTO;
import mx.fes.unam.model.entities.Venta.VentaEntity;
import mx.fes.unam.model.entities.Venta.VentaListDTO;
import mx.fes.unam.model.entities.libro.LibroEntity;
import mx.fes.unam.repository.VentaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class VentaMapper {


    public VentaDTO toDTO (VentaEntity entity){

        if (entity == null){
            return null;
        }

        VentaDTO dto = VentaDTO.builder()
                .id(entity.getId())
                .fecha(entity.getFecha())
                .cantidad(entity.getCantidad())
                .total(entity.getTotal())
                .usuarioId(entity.getUsuario() != null ?  entity.getUsuario().getId() : null)
                .libroId(entity.getLibro() != null ? entity.getLibro().getId() : null ).build();

        return dto;
    }

    public VentaEntity toEntity (VentaDTO dto, UsuarioEntity usuarioEntity, LibroEntity libroEntity){

        if (dto == null) {

            return null;
        }

        VentaEntity entity = VentaEntity.builder()
                .id(dto.getId())
                .fecha(dto.getFecha())
                .cantidad(dto.getCantidad())
                .total(dto.getTotal())
                .usuario(usuarioEntity)
                .libro(libroEntity).build();
        
        return entity;
    }



    public VentaListDTO toListDTO(VentaEntity entity){

        return VentaListDTO.builder()
                .id(entity.getId())
                .fecha(entity.getFecha())
                .cantidad(entity.getCantidad())
                .total(entity.getTotal())
                .nombreUsuario(entity.getUsuario().getNombre())
                .tituloLibro(entity.getLibro().getTitulo())
                .build();
    }



}
