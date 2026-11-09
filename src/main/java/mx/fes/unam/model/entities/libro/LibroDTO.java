package mx.fes.unam.model.entities.libro;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibroDTO {

    private Long id;

    @NotBlank(message = "El título no puede estar en blanco")
    @Size(min = 2, max = 200, message = "El título debe tener entre 2 y 200 caracteres")
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    @Size(min = 2, max = 150, message = "El autor debe tener entre 2 y 150 caracteres")
    private String autor;

    @NotBlank(message = "El ISBN es obligatorio")
    @Pattern(regexp = "^(97[89])?\\d{9}[\\dX]$|^[A-Za-z0-9\\-]{5,20}$", message = "El formato del ISBN no es válido")
    private String isbn;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", inclusive = true, message = "El precio debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "El precio tiene un formato inválido (máximo 8 enteros y 2 decimales)")
    private BigDecimal precio;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Max(value = 99999, message = "El stock no puede exceder las 99,999 unidades")
    private Integer stock;
}