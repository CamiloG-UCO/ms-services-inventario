Feature: Registro de productos
  Como empleado, quiero registrar productos consumibles y asignarlos a una habitación,
  para llevar control del consumo por reserva.
  Scenario: Crear nuevo producto consumible y asignarlo a habitación
    Given el inventario del hotel "Santa Marta Resort"
    When el usuario "ana.garcia" ingrese producto "agua mineral 500ml", categoría "bebida", tipo "Consumible", cantidad "5", unidad "unidades", ubicacion "H-456"
    Then el sistema debe registrar el producto con código "INV-001"
    And mostrar mensaje "Producto registrado exitosamente"