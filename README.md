# 🚗 API REST Spring Boot: Gestión de Coches y Marcas con JWT

Proyecto de desarrollado. Se trata de una solución Backend robusta construida con **Spring Boot 3** y **Java 17** que implementa una API REST completa para la administración de un catálogo de vehículos y sus fabricantes, protegida mediante seguridad *stateless* basada en tokens.


---

### 🛠️ Stack Tecnológico Dominado

| Tecnología / Herramienta | Descripción / Rol en el Proyecto |
| :--- | :--- |
| **Spring Boot 3** | Framework principal para el desarrollo ágil de la aplicación. |
| **Spring Security** | Mecanismo central para la autenticación, control de accesos y protección. |
| **JWT (jjwt)** | Generación y validación de tokens de autenticación totalmente stateless. |
| **Spring Data JPA** | Abstracción de persistencia y consultas automatizadas a la base de datos. |
| **Hibernate ORM** | Mapeo objeto-relacional para la gestión de entidades y relaciones. |
| **MySQL 8** | Sistema de gestión de bases de datos relacional para el almacenamiento persistente. |
| **Thymeleaf** | Motor de plantillas integrado para renderizar vistas server-side en el mismo proyecto. |
| **OpenAPI 3 / Swagger UI** | Generación automatizada de documentación interactiva de endpoints en `/swagger-ui.html`. |
| **Maven** | Ciclo de vida del software, compilación y gestión de dependencias externas. |

---

## 🏗️ Estructura y Arquitectura del Proyecto

La aplicación se ha diseñado siguiendo una **arquitectura limpia en capas** bien definidas para asegurar la separación de responsabilidades, la mantenibilidad y la escalabilidad del código:

```text
src/main/java/com/proyecto/
│
├── modelo/          # Entidades de persistencia (Coche, Marca, Usuario)
├── repositorio/     # Interfaces de acceso a datos (Spring Data JPA)
├── servicio/        # Capa lógica de negocio (Interfaces e Implementaciones)
├── controlador/     # Controladores REST y Controladores de Vistas (Thymeleaf)
└── seguridad/       # Filtros, utilidades JWT y configuración de Spring Security
```

**Flujo de la Aplicación:**
Controlador (REST/Vistas) ➡️ Capa de Servicio ➡️ Repositorio (JPA) ➡️ Base de Datos (MySQL)

**📊 Modelo de Datos (MySQL)**
El sistema gestiona de forma profesional las relaciones entre entidades mediante JPA, aplicando anotaciones para evitar
problemas clásicos de recursividad en las respuestas JSON (@JsonManagedReference y @JsonBackReference).

**Relaciones Clave:**
· Marca ➡️ Coche (OneToMany): Una marca puede fabricar múltiples coches.

· Coche ➡️ Marca (ManyToOne): Cada coche pertenece obligatoriamente a una única marca con una clave foránea marca_id.

### 🔐 Seguridad y Control de Acceso (JWT + Roles)
La autenticación se realiza de manera desacoplada a través de un filtro personalizado que intercepta las peticiones HTTP, extrae el token de las cabeceras (Authorization: Bearer <token>), 
valida su firma, comprueba su fecha de expiración y carga el contexto de seguridad si el token es lícito.

**Niveles de Autorización por Roles:**
· USER: Acceso de solo lectura a los catálogos públicos de vehículos y marcas.

· ADMIN: Permisos completos de escritura (Creación, modificación y borrado) sobre recursos y gestión de usuarios.

· SYSTEM: Rol técnico especializado con acceso exclusivo a endpoints críticos del sistema (ej. bloqueo de cuentas).

### 🛣️ Matriz de Endpoints y Permisos de la API

| Método | Endpoint | Descripción | Requisito de Seguridad |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/v1/coches` | Listar catálogo completo de coches | 🔓 Público / Libre acceso |
| **GET** | `/api/v1/coches/{id}` | Obtener detalle de un coche específico | 🔓 Público / Libre acceso |
| **POST** | `/api/v1/coches` | Registrar un nuevo coche en el sistema | 🔐 JWT Válido |
| **PUT** | `/api/v1/coches/{id}` | Actualizar datos de un coche existente | 🔐 JWT Válido |
| **DELETE** | `/api/v1/coches/{id}` | Eliminar un coche del catálogo | 🔐 JWT Válido |
| **GET** | `/api/v1/marcas` | Listar todas las marcas registradas | 🔓 Público / Libre acceso |
| **POST** | `/api/v1/marcas` | Registrar una nueva marca fabricante | 🔐 JWT Válido |
| **PUT** | `/api/v1/marcas` | Modificar datos de una marca | 🔐 JWT Válido |
| **DELETE** | `/api/v1/marcas/{id}` | Eliminar una marca del sistema | 🔐 JWT Válido |
| **POST** | `/api/v1/usuarios` | Crear y gestionar cuentas de usuario | 🛡️ JWT + Rol **ADMIN** |
| **PUT** | `/api/v1/usuarios/bloquear` | Bloquear de forma inmediata un usuario | ⚙️ JWT + Rol **SYSTEM** |
---

###💎 Manejo de Códigos de Respuesta HTTP
Para cumplir estrictamente con los estándares RESTful, los controladores devuelven respuestas semánticas precisas 
acompañadas del estado HTTP correspondiente a cada situación:

· 200 OK: Petición procesada con éxito que retorna datos en el cuerpo.

· 201 Created: Inserción o registro completado exitosamente (utilizado en peticiones POST).

· 204 No Content: Acción realizada con éxito que no requiere devolver datos de respuesta (ej. tras un DELETE).

· 401 Unauthorized: Intento de acceso denegado debido a un token JWT ausente, inválido o expirado.

· 404 Not Found: El recurso solicitado (coche, marca o usuario) no existe en la base de datos.

##🎯 Conclusiones del Proyecto
Este desarrollo consolida e integra de manera práctica los conocimientos avanzados adquiridos a lo largo del módulo formativo, 
destacando por los siguientes hitos alcanzados:

1.Diseño RESTful Estándar: Arquitectura limpia que independiza la lógica de presentación de la lógica de datos mediante controladores optimizados.

2.Persistencia Avanzada: Control exhaustivo de relaciones complejas en JPA sin generar bucles infinitos en el mapeo relacional.

3.Seguridad Robusta: Implementación eficaz de una seguridad desacoplada (stateless), lo que incrementa notablemente el rendimiento general de la API frente a los sistemas tradicionales basados en sesión de servidor.

4.Auto-Documentación: Integración nativa de entornos interactivos para facilitar el testeo rápido del equipo frontend o evaluadores externos.


###👤 Autoría y Contacto
Desarrollado por Valentina Castillo Escobar
| Enlace de Contacto | URL |
| :--- | :--- |
| 🌐 **Sitio Web** | [valentina-castillo.github.io](https://valentina-castillo.github.io/) |
| 💼 **LinkedIn** | [linkedin.com/in/valentina-castillo](https://www.linkedin.com/in/valentina-castillo-191863202/) |
