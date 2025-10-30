Feature: Alerta automática por stock mínimo
  Como sistema de inventario del hotel
  Quiero detectar productos por debajo del stock mínimo
  Para notificar al área de compras y evitar desabastecimiento

  Scenario: Notificar cuando un producto está por debajo del stock mínimo
    Given existe un producto llamado "Shampoo individual"
    And la cantidad mínima configurada para el producto es "30"
    And la cantidad actual del inventario es "25"
    When el sistema ejecuta la validación diaria de inventarios
    Then se debe enviar una notificación por correo a "compras@hotel.com"
    And el asunto del correo debe ser "Alerta: stock bajo Shampoo individual"
