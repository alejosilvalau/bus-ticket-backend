# AGENTS.md
## Project Structure

Feature-based and layered division, following the MVC (Model-View-Controller) design pattern and a layered software architecture. Each feature has its own folder containing subfolders for controllers, services, repositories, and entities. Additionally, there is a common folder for global exceptions, configurations, and utilities that can be shared across the different features.

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

Internally, the code should be organized as follows:

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

In other words, the project is organized by features (identity, fleet, journey, booking) and by layers (controllers, services, repositories, entities).
