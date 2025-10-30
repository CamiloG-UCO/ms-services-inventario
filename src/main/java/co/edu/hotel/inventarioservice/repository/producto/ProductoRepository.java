package co.edu.hotel.inventarioservice.repository.producto;

import co.edu.hotel.inventarioservice.domain.producto.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    ProductoEntity findByBarcode(String barcode);
}

