Feature: Actualización de stock
  Como sistema de inventario del hotel
  Quiero disminuir el stock cuando un producto sea usado en housekeeping
  Para mantener actualizado el inventario

  Scenario: Descontar cantidad tras uso en limpieza
    Given el producto "agua mineral 500ml" con cantidad actual "50" en almacén central
    When el usuario "sofia.mendez" registre consumo de "10 unidades" para la habitación "H-456"
    Then el sistema debe actualizar la cantidad a "40 unidades"
    And registrar movimiento "consumo_housekeeping: H-456" por "sofia.mendez"
