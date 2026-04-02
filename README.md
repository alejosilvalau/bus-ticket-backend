# Bus Ticket
## Integrantes
- 47868, Silva Alejo Lautaro
- Valentino

## Enunciado General
El sistema de gestión de boletos para colectivos de larga distancia en Argentina es una plataforma integral diseñada para facilitar la compra, uso y administración de pasajes en rutas interurbanas, como por ejemplo el trayecto Buenos Aires – Rosario. Los usuarios pueden consultar horarios, seleccionar servicios según categorías (común, semicama, cama, ejecutivo), reservar o adquirir sus boletos de manera anticipada y recibir confirmación digital inmediata.

Además, el sistema contempla la aplicación automática de beneficios sociales para públicos específicos: descuentos para jubilados, estudiantes, personas con discapacidad, o beneficiarios de planes sociales, conforme a la normativa vigente. Esta solución busca agilizar la experiencia del pasajero, reducir trámites presenciales y optimizar el control por parte de empresas y organismos regulatorios, asegurando transparencia y equidad en el acceso a servicios de transporte de larga distancia en el país.


## Casos de Uso para la AP DIRECTA
| Requerimiento | Detalle/Listado de casos incluidos |
| --- | --- |
| ABMC | User, Driver, Bus, Trip, Ticket, Discount, Destination, Seat |
| CU "Complejo"(nivel resumen) | En un sistema de venta de pasajes verificar que el asiento del viaje esté libre, que descuentos y beneficios tiene el pasajero y aplicar los necesarios variando el sub-total. Esto se realiza en la compra del pasaje. Despés en otra instancia, se imprime. Entonces queda: 1. Compra pasaje, 2. Verificacion beneficios, 3. Impresion de boleto () |
| Listado complejo | Listado de viajes disponibles, pudiendose filtrar por atributos como el destino, el punto de partida, el precio o fecha y hora de salida  |
| Nivel de acceso | User y Admin |
|Manejo de errores| Mensajes en la UI de retorno en API |
| publicar el sitio | No obligatorio, hacerlo con AWS |

### Requerimientos extra - AD
| Requerimiento | Detalle/Listado de casos incluidos |
| --- | --- |
| Manejo de archivos | |
| Custom exceptions | Excepciones personalizadas mediante subclases, validando las reglas de negocio |
| Log de errores |  |
| Envio de emails |  |
