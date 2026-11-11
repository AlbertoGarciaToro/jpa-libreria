package mx.fes.unam.service.venta;

import lombok.RequiredArgsConstructor;
import mx.fes.unam.model.entities.Usuario.UsuarioEntity;
import mx.fes.unam.model.entities.Venta.VentaDTO;
import mx.fes.unam.model.entities.Venta.VentaEntity;
import mx.fes.unam.model.entities.Venta.VentaListDTO;
import mx.fes.unam.model.entities.libro.LibroEntity;
import mx.fes.unam.repository.LibroRepository;
import mx.fes.unam.repository.UsuarioRepository;
import mx.fes.unam.repository.VentaRepository;
import mx.fes.unam.service.libro.LibroMapper;
import mx.fes.unam.service.libro.LibroService;
import mx.fes.unam.service.usuario.UsuarioMapper;
import mx.fes.unam.service.usuario.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;

    private final LibroRepository libroRepository;
    private final UsuarioRepository usuarioRepository;



//    si el id que le pasamos ya existe en la DB , solo actuzliza el registro
    public VentaDTO guardarVenta(VentaDTO dto){

        UsuarioEntity usuarioEntity = usuarioRepository.findById(dto.getUsuarioId()).orElseThrow(() ->new RuntimeException("Usuario no encontrado..."));

        LibroEntity libroEntity = libroRepository.findById(dto.getLibroId()).orElseThrow(() -> new RuntimeException("Libro no encontrado..."));
        VentaEntity entity = ventaRepository.save(ventaMapper.toEntity(dto,usuarioEntity,libroEntity));

        return ventaMapper.toDTO(entity);
    }

    public VentaDTO obtenerVenta (Long id){

        VentaEntity entity = ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrada con ID:" + id));
        return ventaMapper.toDTO(entity);
    }

    public List<VentaDTO> listaVentas(){

        return ventaRepository.findAll().stream().map(ventaMapper::toDTO).toList();
    }

    public void eliminarVenta (Long id){

        ventaRepository.deleteById(id);
    }

    public List<VentaListDTO> listarVentasConDetalle(){

        return ventaRepository.findAll()
                .stream().map(ventaMapper::toListDTO)
                .toList();
    }





}
