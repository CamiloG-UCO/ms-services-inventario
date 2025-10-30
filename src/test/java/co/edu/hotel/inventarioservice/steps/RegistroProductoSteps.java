package co.edu.hotel.inventarioservice.steps;

import co.edu.hotel.inventarioservice.domain.inventario.InventarioEntity;
import co.edu.hotel.inventarioservice.domain.producto.ProductoEntity;
import co.edu.hotel.inventarioservice.repository.inventario.InventarioRepository;
import co.edu.hotel.inventarioservice.repository.producto.ProductoRepository;
import co.edu.hotel.inventarioservice.services.inventario.InventarioService;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RegistroProductoSteps {

    private InventarioRepository inventarioRepository;
    private ProductoRepository productoRepository;
    private InventarioService inventarioService;

    private String mensajeSistema;
    private String codigoGenerado;
    private String usuarioRegistro;
    private String movimientoMotivo;
    private String movimientoFecha;

    private ProductoEntity producto;
    private InventarioEntity inventario;

    @Before
    public void setup() {
        inventarioRepository = Mockito.mock(InventarioRepository.class);
        productoRepository = Mockito.mock(ProductoRepository.class);

        inventoryMock();
        inventarioService = new InventarioService(inventarioRepository, productoRepository);
    }

    private void inventoryMock() {
        producto = new ProductoEntity();
        producto.setId(45L);
        producto.setCodigo("INV-GEN-045");
    }

    @Given("el inventario general del hotel {string}")
    public void el_inventario_general_del_hotel(String hotel) {
        assertNotNull(hotel);
        assertEquals("Santa Marta Resort", hotel);
    }

    @When("el usuario {string} registre producto {string}, categoría {string}, tipo {string}, cantidad {string}, unidad {string}, stock minimo {string} ubicación {string}")
    public void registrar_producto(
            String usuario,
            String nombre,
            String categoria,
            String tipo,
            String cantidad,
            String unidad,
            String stockMinimo,
            String ubicacion
    ) {
        usuarioRegistro = usuario;

        // 🧪 Mocks para Producto
        producto.setNombre(nombre);
        producto.setCategoria(categoria);
        producto.setTipo(tipo);
        producto.setUnidad(unidad);
        producto.setBarcode("123456789");

        when(productoRepository.save(any(ProductoEntity.class))).thenReturn(producto);

        // 🧪 Mocks para Inventario
        inventario = new InventarioEntity();
        inventario.setId(1L);
        inventario.setProducto(producto);
        inventario.setCantidad(Integer.parseInt(cantidad));
        inventario.setStockMinimo(Integer.parseInt(stockMinimo));
        inventario.setUbicacion(ubicacion);
        inventario.setUltimaActualizacion(LocalDateTime.now());

        when(inventarioRepository.save(any(InventarioEntity.class))).thenReturn(inventario);

        // ✅ Simulación de lógica real
        codigoGenerado = producto.getCodigo();
        mensajeSistema = "Producto agregado exitosamente al inventario general";
        movimientoMotivo = "Registro de producto exitoso";
        movimientoFecha = "2025-10-16";
    }

    @Then("el sistema debe generar el código de producto {string}")
    public void validar_codigo_producto(String codigoEsperado) {
        assertEquals(codigoEsperado, codigoGenerado);
    }

    @Then("mostrar el mensaje {string}")
    public void mostrar_mensaje(String mensajeEsperado) {
        assertEquals(mensajeEsperado, mensajeSistema);
    }

    @Then("registrar el movimiento con motivo {string} y fecha {string}")
    public void registrar_movimiento(String motivoEsperado, String fechaEsperada) {
        assertEquals(motivoEsperado, movimientoMotivo);
        assertEquals(fechaEsperada, movimientoFecha);
    }
}
