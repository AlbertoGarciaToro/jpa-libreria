package mx.fes.unam.model.entities.libro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroDTO{
    Long id;
    String autor;
    BigDecimal precio;
}
