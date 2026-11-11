package mx.fes.unam.repository;

import mx.fes.unam.model.entities.Venta.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<VentaEntity, Long> {
}
