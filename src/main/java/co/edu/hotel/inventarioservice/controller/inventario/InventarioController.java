package co.edu.hotel.inventarioservice.controller.inventario;

import co.edu.hotel.inventarioservice.domain.inventario.InventarioEntity;
import co.edu.hotel.inventarioservice.services.inventario.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @PostMapping("/registrar")
    public ResponseEntity<InventarioEntity> registrarProductoEnInventario(
            @RequestParam Long productoId,
            @RequestParam Integer cantidad,
            @RequestParam String ubicacion) {

        return ResponseEntity.ok(
                inventarioService.registrarProductoEnInventario(productoId, cantidad, ubicacion)
        );
    }

    @GetMapping
    public ResponseEntity<List<InventarioEntity>> obtenerInventario() {
        return ResponseEntity.ok(inventarioService.obtenerInventario());
    }

    @PutMapping("/actualizar-stock")
    public ResponseEntity<InventarioEntity> actualizarStock(
            @RequestParam Long productoId,
            @RequestParam String ubicacion,
            @RequestParam Integer cantidadNueva) {

        return ResponseEntity.ok(
                inventarioService.actualizarStock(productoId, ubicacion, cantidadNueva)
        );
    }

    @GetMapping("/bajo-stock")
    public ResponseEntity<List<InventarioEntity>> validarStockMinimo() {
        return ResponseEntity.ok(inventarioService.validarStockMinimo());
    }
}


