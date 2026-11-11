package mx.fes.unam.model.entities.Venta;

import jakarta.validation.constraints.*;
import lombok.*;
import mx.fes.unam.model.entities.Usuario.UsuarioEntity;
import mx.fes.unam.model.entities.libro.LibroEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDTO {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fecha;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "Debe venderse al menos 1 unidad")
    @Max(value = 100, message = "No se pueden vender más de 100 unidades en una sola transacción")
    private Integer cantidad;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.01", inclusive = true, message = "El total debe ser mayor a cero")
    private BigDecimal total;

    @NotNull(message = "Debe seleccionar un usuario válido")
    private Long usuarioId;

    @NotNull(message = "Debe seleccionar un libro válido")
    private Long libroId;
}
