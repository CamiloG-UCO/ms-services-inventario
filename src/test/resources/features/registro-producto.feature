Feature: Registro de productos generales

  Scenario: Ingresar nuevo producto al inventario principal
    Given el inventario general del hotel "Santa Marta Resort"
    When el usuario "sofia.mendez" registre producto "agua mineral x500ml", categoría "bebidas", tipo "Consumible", cantidad "30", unidad "unidades", stock minimo "6" ubicación "Almacén"
    Then el sistema debe generar el código de producto "INV-GEN-045"
    And mostrar el mensaje "Producto agregado exitosamente al inventario general"
    And registrar el movimiento con motivo "Registro de producto exitoso" y fecha "2025-10-16"
