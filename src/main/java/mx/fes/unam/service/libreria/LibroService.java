package mx.fes.unam.service.libreria;

import mx.fes.unam.model.entities.libro.LibroDTO;
import mx.fes.unam.model.entities.libro.LibroEntity;
import mx.fes.unam.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    //@Autowired
    private final LibroMapper libroMapper;

    //@Autowired
    private final LibroRepository libroRepository;

    public LibroService(LibroMapper libroMapper,
                        LibroRepository libroRepository) {
        this.libroMapper = libroMapper;
        this.libroRepository = libroRepository;
    }

    public LibroDTO guardarLibro(LibroDTO libro) {
        LibroEntity lib = libroRepository.save(libroMapper.toEntity(libro));
        libro=libroMapper.toDTO(lib);
        return libro;
    }

    public LibroDTO obtenerLibro(Long id) {
        return libroMapper.toDTO(
                libroRepository.findById(id).orElse(null));
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    public List<LibroDTO> listarLibros() {
        return libroRepository.findAll()
                .stream()
                .map(l->libroMapper.toDTO(l))
                .toList();
    }

}
