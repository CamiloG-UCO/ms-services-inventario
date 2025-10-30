package co.edu.hotel.inventarioservice.domain.producto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigo; // Ej: INV-GEN-045 o INV-001

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String categoria; // bebidas, aseo, etc.

    @Column(nullable = false)
    private String tipo; // Consumible, Activo

    @Column(nullable = false)
    private String unidad; // unidades, litros, etc.

    @Column(name = "barcode", unique = true)
    private String barcode;
}


