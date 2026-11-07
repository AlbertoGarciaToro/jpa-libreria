package mx.fes.unam.service.usuario;

import mx.fes.unam.model.entities.Usuario.UsuarioDTO;
import mx.fes.unam.model.entities.Usuario.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO (UsuarioEntity entity){

        if (entity == null){
            return  null;
        }
        UsuarioDTO dto = UsuarioDTO.builder().id(entity.getId())
                .nombre(entity.getNombre())
                .email(entity.getEmail())
                .telefono(entity.getTelefono())
                .build();

        return dto;
    }

    public UsuarioEntity toEntity (UsuarioDTO dto){

        if (dto == null){

            return null;
        }

        UsuarioEntity entity = UsuarioEntity.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .build();

        return entity;
    }


}
