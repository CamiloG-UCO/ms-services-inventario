package co.edu.hotel.inventarioservice.repository.inventario;

import co.edu.hotel.inventarioservice.domain.inventario.InventarioEntity;
import co.edu.hotel.inventarioservice.domain.producto.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<InventarioEntity, Long> {

    // Buscar inventario por producto
    Optional<InventarioEntity> findByProducto(ProductoEntity producto);

    // Buscar inventario por producto y ubicación (por si tienes inventario en diferentes puntos)
    Optional<InventarioEntity> findByProductoAndUbicacion(ProductoEntity producto, String ubicacion);

}

