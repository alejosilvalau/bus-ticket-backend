# Bus Ticket
## Integrantes
- 47868, Silva Alejo Lautaro
- Valentino

## Enunciado General
El sistema permite gestionar integralmente la compra, reserva y administración de boletos para colectivos de larga distancia en Argentina, incluyendo la consulta de horarios, listado de viajes filtrable por atributos como destino, fecha, hora y precio, manejo de asientos libres, verificación automática de descuentos por beneficios sociales en la reserva y emisión del pasaje en formato digital o PDF. 

Incorpora perfiles de usuario y administrador, manejo de errores con mensajes claros en la interfaz, y excepciones personalizadas, asegurando eficiencia, accesibilidad y control para pasajeros, empresas y entes reguladores.


## Casos de Uso para la AP DIRECTA
| Requerimiento | Detalle/Listado de casos incluidos |
| --- | --- |
| ABMC | User, Driver, Bus, Trip, Ticket, Discount, Destination, Seat |
| CU "Complejo"(nivel resumen) | Reserva de ticket, que incluye la verificación de la disponibilidad de asiento y de descuentos por beneficios sociales, e Impresión de boleto en formato pdf |
| Listado complejo | Listado de viajes disponibles, pudiendose filtrar por atributos como el destino, el punto de partida, el precio o fecha y hora de salida  |
| Nivel de acceso | User y Admin |
|Manejo de errores| Mensajes en la UI de retorno en API |
| publicar el sitio | No obligatorio, hacerlo con AWS si entra en tier gratis |

### Requerimientos extra - AD
| Requerimiento | Detalle/Listado de casos incluidos |
| --- | --- |
| Custom exceptions | Excepciones personalizadas mediante subclases, validando las reglas de negocio |
