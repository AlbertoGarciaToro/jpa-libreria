package mx.fes.unam.model.entities.Venta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaListDTO {

    private Long id;
    private LocalDateTime fecha;
    private Integer cantidad;
    private BigDecimal total;
    private String nombreUsuario;
    private String tituloLibro;
}
