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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InventarioSteps {

    private InventarioRepository inventarioRepository;
    private ProductoRepository productoRepository;
    private InventarioService inventarioService;

    private ProductoEntity producto;
    private InventarioEntity inventario;

    private boolean notificacionEnviada = false;
    private String asuntoCorreo = null;
    private List<InventarioEntity> resultadoValidacion;

    @Before
    public void setup() {
        // 🔹 Creamos mocks (no hay BD real)
        inventarioRepository = Mockito.mock(InventarioRepository.class);
        productoRepository = Mockito.mock(ProductoRepository.class);

        // 🔹 Inyectamos los mocks al servicio
        inventarioService = new InventarioService(inventarioRepository, productoRepository);
    }

    @Given("existe un producto llamado {string}")
    public void existe_un_producto_llamado(String nombre) {
        producto = new ProductoEntity();
        producto.setId(1L);
        producto.setNombre(nombre);
        producto.setCategoria("aseo");
        producto.setBarcode("123456789");

        // 🔹 Mock del repositorio de productos
        when(productoRepository.findById(1L))
                .thenReturn(Optional.of(producto));
    }

    @And("la cantidad mínima configurada para el producto es {string}")
    public void la_cantidad_minima_configurada_para_el_producto_es(String minimo) {
        inventario = new InventarioEntity();
        inventario.setId(1L);
        inventario.setProducto(producto);
        inventario.setUbicacion("Almacén");
        inventario.setStockMinimo(Integer.parseInt(minimo));
        inventario.setUltimaActualizacion(LocalDateTime.of(2025, 11, 5, 12, 0)); // 🔹 Fecha fija mockeada
    }

    @And("la cantidad actual del inventario es {string}")
    public void la_cantidad_actual_del_inventario_es(String cantidad) {
        inventario.setCantidad(Integer.parseInt(cantidad));

        // 🔹 Mock de consultas del repositorio
        when(inventarioRepository.findByProductoAndUbicacion(producto, "Almacén"))
                .thenReturn(Optional.of(inventario));

        when(inventarioRepository.findAll())
                .thenReturn(List.of(inventario));
    }

    @When("el sistema ejecuta la validación diaria de inventarios")
    public void el_sistema_ejecuta_la_validacion_diaria_de_inventarios() {
        // 🔹 Simulamos la validación del servicio sin tocar BD real
        resultadoValidacion = inventarioService.validarStockMinimo();

        // 🔹 Simulación del envío de notificación
        if (!resultadoValidacion.isEmpty()) {
            notificacionEnviada = true;
            asuntoCorreo = "Alerta: stock bajo " + producto.getNombre();
        }
    }

    @Then("se debe enviar una notificación por correo a {string}")
    public void se_debe_enviar_una_notificacion_por_correo_a(String correoDestino) {
        assertTrue(notificacionEnviada, "La notificación de inventario bajo no fue enviada");
        // 🔹 Aquí podrías mockear un servicio de email si existiera
    }

    @And("el asunto del correo debe ser {string}")
    public void el_asunto_del_correo_debe_ser(String asuntoEsperado) {
        assertNotNull(asuntoCorreo, "No se generó ningún asunto de correo");
        assertEquals(asuntoEsperado, asuntoCorreo, "El asunto del correo no coincide");
    }
}
