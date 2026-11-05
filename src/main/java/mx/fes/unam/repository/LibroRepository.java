package mx.fes.unam.repository;

import mx.fes.unam.model.entities.libro.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<LibroEntity,Long> {
    List<LibroEntity> findByAutorContainingIgnoreCase(String autor);
    List<LibroEntity> findByTituloContainingIgnoreCase(String titulo);
}
