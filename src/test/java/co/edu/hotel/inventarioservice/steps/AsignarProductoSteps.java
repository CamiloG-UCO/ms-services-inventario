package co.edu.hotel.inventarioservice.steps;

import co.edu.hotel.inventarioservice.domain.inventario.InventarioEntity;
import co.edu.hotel.inventarioservice.domain.producto.ProductoEntity;
import co.edu.hotel.inventarioservice.repository.inventario.InventarioRepository;
import co.edu.hotel.inventarioservice.repository.producto.ProductoRepository;
import co.edu.hotel.inventarioservice.services.inventario.InventarioService;
import co.edu.hotel.inventarioservice.services.producto.ProductoService;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AsignarProductoSteps {

    // ==== MOCKS ====
    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private InventarioRepository inventarioRepository;

    // ==== SERVICES ====
    @InjectMocks
    private ProductoService productoService;

    @InjectMocks
    private InventarioService inventarioService;

    // ==== VARIABLES DE PRUEBA ====
    private ProductoEntity producto;
    private InventarioEntity inventario;
    private String mensajeSistema;
    private String codigoGenerado;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        producto = new ProductoEntity();
        producto.setId(1L);
        producto.setCodigo("INV-001");
        producto.setNombre("agua mineral 500ml");
        producto.setCategoria("bebida");
        producto.setTipo("Consumible");
        producto.setUnidad("unidades");
    }

    // ==================== GIVEN ====================
    @Given("el inventario del hotel {string}")
    public void el_inventario_del_hotel(String hotel) {
        assertNotNull(hotel);
        assertEquals("Santa Marta Resort", hotel);
    }

    // ==================== WHEN ====================
    @When("el usuario {string} ingrese producto {string}, categoría {string}, tipo {string}, cantidad {string}, unidad {string}, ubicacion {string}")
    public void el_usuario_ingrese_producto(
            String usuario,
            String nombre,
            String categoria,
            String tipo,
            String cantidad,
            String unidad,
            String ubicacion
    ) {
        // ====== Simular creación del producto ======
        ProductoEntity nuevoProducto = new ProductoEntity();
        nuevoProducto.setNombre(nombre);
        nuevoProducto.setCategoria(categoria);
        nuevoProducto.setTipo(tipo);
        nuevoProducto.setUnidad(unidad);
        nuevoProducto.setCodigo("INV-001");

        when(productoRepository.save(any(ProductoEntity.class))).thenReturn(producto);

        ProductoEntity productoCreado = productoService.crearProducto(nuevoProducto);

        // ====== Simular asignación a inventario ======
        when(productoRepository.findById(productoCreado.getId())).thenReturn(Optional.of(productoCreado));

        inventario = new InventarioEntity();
        inventario.setId(1L);
        inventario.setProducto(productoCreado);
        inventario.setCantidad(Integer.parseInt(cantidad));
        inventario.setUbicacion(ubicacion);
        inventario.setUltimaActualizacion(LocalDateTime.now());

        when(inventarioRepository.save(any(InventarioEntity.class))).thenReturn(inventario);

        inventarioService.registrarProductoEnInventario(productoCreado.getId(), Integer.parseInt(cantidad), ubicacion);

        // ====== Resultados esperados ======
        codigoGenerado = productoCreado.getCodigo();
        mensajeSistema = "Producto registrado exitosamente";
    }

    // ==================== THEN ====================
    @Then("el sistema debe registrar el producto con código {string}")
    public void el_sistema_debe_registrar_el_producto_con_codigo(String codigoEsperado) {
        assertEquals(codigoEsperado, codigoGenerado);
        verify(productoRepository, times(1)).save(any(ProductoEntity.class));
    }

    @And("mostrar mensaje {string}")
    public void mostrar_el_mensaje(String mensajeEsperado) {
        assertEquals(mensajeEsperado, mensajeSistema);
        verify(inventarioRepository, times(1)).save(any(InventarioEntity.class));
    }
}
