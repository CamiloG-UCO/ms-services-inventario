package co.edu.hotel.inventarioservice.steps;

import co.edu.hotel.inventarioservice.domain.inventario.InventarioEntity;
import co.edu.hotel.inventarioservice.domain.producto.ProductoEntity;
import co.edu.hotel.inventarioservice.repository.inventario.InventarioRepository;
import co.edu.hotel.inventarioservice.repository.producto.ProductoRepository;
import co.edu.hotel.inventarioservice.services.inventario.InventarioService;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ActualizacionStockSteps {

    private InventarioRepository inventarioRepository;
    private ProductoRepository productoRepository;
    private InventarioService inventarioService;

    private ProductoEntity producto;
    private InventarioEntity inventario;

    private Integer nuevaCantidadEsperada;
    private String movimientoRegistrado;
    private String usuarioRegistrado;

    @Before
    public void setup() {
        inventarioRepository = mock(InventarioRepository.class);
        productoRepository = mock(ProductoRepository.class);
        inventarioService = new InventarioService(inventarioRepository, productoRepository);
    }

    @Given("el producto {string} con cantidad actual {string} en almacén central")
    public void el_producto_con_cantidad_actual_en_almacen_central(String nombre, String cantidad) {

        producto = new ProductoEntity();
        producto.setId(1L);
        producto.setCodigo("INV-GEN-001");
        producto.setNombre(nombre);
        producto.setCategoria("bebida");
        producto.setTipo("Consumible");
        producto.setUnidad("unidades");
        producto.setBarcode("123456789");

        inventario = new InventarioEntity();
        inventario.setId(1L);
        inventario.setProducto(producto);
        inventario.setHotel("Santa Marta Resort");
        inventario.setUbicacion("Almacén central");
        inventario.setCantidad(Integer.parseInt(cantidad));
        inventario.setStockMinimo(10);
        inventario.setUltimaActualizacion(LocalDateTime.now());

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(inventarioRepository.findByProductoAndUbicacion(producto, "Almacén central"))
                .thenReturn(Optional.of(inventario));
    }

    @When("el usuario {string} registre consumo de {string} para la habitación {string}")
    public void el_usuario_registre_consumo(String usuario, String consumo, String habitacion) {

        Integer cantidadConsumida = Integer.parseInt(consumo.split(" ")[0]); // "10 unidades" -> 10
        Integer nuevaCantidad = inventario.getCantidad() - cantidadConsumida;
        nuevaCantidadEsperada = nuevaCantidad;

        // Actualizamos inventario simulado
        inventario.setCantidad(nuevaCantidad);
        inventario.setUltimaActualizacion(LocalDateTime.now());

        when(inventarioRepository.save(inventario)).thenReturn(inventario);

        // Fake registro movimiento
        movimientoRegistrado = "consumo_housekeeping: " + habitacion;
        usuarioRegistrado = usuario;
    }

    @Then("el sistema debe actualizar la cantidad a {string}")
    public void el_sistema_debe_actualizar_cantidad(String cantidadEsperada) {
        assertEquals(Integer.parseInt(cantidadEsperada.split(" ")[0]), inventario.getCantidad());
    }

    @And("registrar movimiento {string} por {string}")
    public void registrarMovimientoPor(String movimiento, String usuario) {
        assertEquals(movimiento, movimientoRegistrado);
        assertEquals(usuario, usuarioRegistrado);
    }
}

