# Normas Generales
## Estructura de proyecto

Division por features y capas, siguiendo el patrón de diseño MVC (Model-View-Controller) y la arquitectura de software en capas. Cada feature tiene su propia carpeta con subcarpetas para controllers, services, repositories y entities. Además, hay una carpeta común para excepciones globales, configuraciones y utilidades que pueden ser compartidas entre las diferentes features.

```
src/main/java/com/app/
│
├── features/
│   ├── identity/
│   │   ├── controllers/
│   │   ├── services/
│   │   ├── repositories/
│   │   └── entities/ (Person, User, Driver)
│   │
│   ├── fleet/
│   │   ├── controllers/
│   │   ├── entities/ (Bus, Seat, SeatType)
│   │   └── ...
│   │
│   ├── journey/
│   │   ├── entities/ (Trip, Location)
│   │   └── ...
│   │
│   └── booking/
│       ├── entities/ (Ticket)
│       └── ...
│
└── common/ (Excepciones globales, configuraciones, utilidades)
```
