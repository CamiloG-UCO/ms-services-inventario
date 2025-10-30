package co.edu.hotel.inventarioservice.domain.movimiento;

import co.edu.hotel.inventarioservice.domain.inventario.InventarioEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_inventario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventario_id", nullable = false)
    private InventarioEntity inventario;

    private String usuario; // "sofia.mendez"
    private String motivo; // consumo_housekeeping: H-456, Registro exitoso, etc.
    private Integer cantidad; // Positivo entrada, negativo salida
    private LocalDateTime fecha;
}

