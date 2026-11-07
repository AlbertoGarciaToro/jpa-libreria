    package mx.fes.unam.service.usuario;

import mx.fes.unam.model.entities.Usuario.UsuarioDTO;
import mx.fes.unam.model.entities.Usuario.UsuarioEntity;
import mx.fes.unam.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
public class UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO guardarUsuario(UsuarioDTO dto){

        UsuarioEntity entity = usuarioRepository.save(usuarioMapper.toEntity(dto));

        return usuarioMapper.toDTO(entity);

    }

    public UsuarioDTO obtenerUsuario (Long id){

        UsuarioEntity entity = usuarioRepository.findById(id).orElse(null);

        return usuarioMapper.toDTO(entity);
    }

    public List<UsuarioDTO> listaUsuarios(){

        return usuarioRepository.findAll().stream().map(usuarioMapper::toDTO).toList();
    }

    public void eliminarUsuario(Long id){

        usuarioRepository.deleteById(id);
    }



}
