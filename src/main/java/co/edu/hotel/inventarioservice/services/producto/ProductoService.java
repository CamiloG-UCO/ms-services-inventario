package co.edu.hotel.inventarioservice.services.producto;

import co.edu.hotel.inventarioservice.domain.producto.ProductoEntity;
import co.edu.hotel.inventarioservice.repository.producto.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoEntity crearProducto(ProductoEntity producto) {
        return productoRepository.save(producto);
    }

    public List<ProductoEntity> obtenerTodos() {
        return productoRepository.findAll();
    }

    public ProductoEntity obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public ProductoEntity actualizarProducto(Long id, ProductoEntity productoActualizado) {
        ProductoEntity producto = obtenerPorId(id);
        producto.setNombre(productoActualizado.getNombre());
        producto.setCategoria(productoActualizado.getCategoria());
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

}

