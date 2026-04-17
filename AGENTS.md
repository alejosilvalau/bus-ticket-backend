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

Internamente, el código se debe organizar de la siguiente manera:

```
features/
└── identity/
    ├── controllers/
    │   ├── AuthController.java       --> (login, logout, password-reset)
    │   └── UserProfileController.java --> (getProfile, updateAvatar)
    ├── services/
    │   ├── LoginService.java         --> (Lógica específica de autenticación)
    │   ├── UserRegistrationService.java --> (Lógica de validación y guardado)
    │   └── DriverManagementService.java --> (Lógica para validar licencias de drivers)
    └── entities/
```

Es decir, una division vertical por features (identity, fleet, journey, booking) y una division horizontal por capas (controllers, services, repositories, entities).
