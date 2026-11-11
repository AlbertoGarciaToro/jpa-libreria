package mx.fes.unam.model.entities.libro;

import jakarta.persistence.*;
import lombok.*;
import mx.fes.unam.model.entities.Venta.VentaEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "libros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"ventas"})
public class LibroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 200)
    private String titulo;

    @Column(nullable = true, length = 150)
    private String autor;

    @Column(unique = true, length = 20)
    private String isbn;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(nullable = true)
    private Integer stock;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VentaEntity> ventas = new ArrayList<>();
}

