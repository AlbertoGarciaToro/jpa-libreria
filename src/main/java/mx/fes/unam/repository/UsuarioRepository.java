package mx.fes.unam.repository;

import mx.fes.unam.model.entities.Usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {

}
