package co.edu.hotel.inventarioservice.domain.inventario;

import co.edu.hotel.inventarioservice.domain.producto.ProductoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventario")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InventarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private ProductoEntity producto;

    @Column(nullable = false)
    private String hotel; // "Santa Marta Resort"

    @Column(nullable = false)
    private String ubicacion; // "Almacén", "H-456"

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Integer stockMinimo;

    private LocalDateTime ultimaActualizacion = LocalDateTime.now();

}

