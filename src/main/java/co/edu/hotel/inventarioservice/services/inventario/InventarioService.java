package co.edu.hotel.inventarioservice.services.inventario;

import co.edu.hotel.inventarioservice.domain.inventario.InventarioEntity;
import co.edu.hotel.inventarioservice.domain.producto.ProductoEntity;
import co.edu.hotel.inventarioservice.repository.inventario.InventarioRepository;
import co.edu.hotel.inventarioservice.repository.producto.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final ProductoRepository productoRepository;

    public InventarioEntity registrarProductoEnInventario(Long productoId, Integer cantidad, String ubicacion) {
        ProductoEntity producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        InventarioEntity inventario = new InventarioEntity();
        inventario.setProducto(producto);
        inventario.setCantidad(cantidad);
        inventario.setUbicacion(ubicacion);
        inventario.setUltimaActualizacion(LocalDateTime.now());

        return inventarioRepository.save(inventario);
    }

    public List<InventarioEntity> obtenerInventario() {
        return inventarioRepository.findAll();
    }

    public InventarioEntity actualizarStock(Long productoId, String ubicacion, Integer cantidadNueva) {
        ProductoEntity producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        InventarioEntity inventario = inventarioRepository.findByProductoAndUbicacion(producto, ubicacion)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado para ese producto/ubicación"));

        inventario.setCantidad(cantidadNueva);
        inventario.setUltimaActualizacion(LocalDateTime.now());

        return inventarioRepository.save(inventario);
    }

    public List<InventarioEntity> validarStockMinimo() {
        List<InventarioEntity> inventarios = inventarioRepository.findAll();
        return inventarios.stream()
                .filter(inv -> inv.getCantidad() <= inv.getStockMinimo())
                .toList();
    }
}

