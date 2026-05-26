# 🚗 API REST — Gestión de Coches y Marcas

API REST desarrollada con **Spring Boot** para la gestión de coches y marcas de vehículos. Incluye autenticación segura mediante **JWT**, documentación interactiva con **Swagger/OpenAPI** y una interfaz web con **Thymeleaf**.

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión |
|---|---|
| Java | 17 |
| Spring Boot | 3.4.1 |
| Spring Security | — |
| Spring Data JPA | — |
| MySQL | 8+ |
| JWT (jjwt) | 0.11.5 |
| Thymeleaf | — |
| Swagger / OpenAPI | 2.7.0 |
| Maven | — |

---

## 📁 Estructura del proyecto

```
src/
├── main/
│   ├── java/com/ipartek/
│   │   ├── componente/         # Filtro JWT y utilidades de token
│   │   ├── configuracion/      # Configuración de Spring Security
│   │   ├── controlador/        # Controladores REST y MVC
│   │   ├── modelo/             # Entidades: Coche, Marca, Usuario
│   │   ├── repositorio/        # Interfaces JPA
│   │   └── servicios/          # Lógica de negocio
│   └── resources/
│       ├── templates/          # Vistas Thymeleaf
│       ├── static/             # CSS e imágenes
│       ├── application.properties
│       └── import.sql          # Datos iniciales
```

---

## ⚙️ Configuración y puesta en marcha

### 1. Requisitos previos

- Java 17+
- Maven
- MySQL 8+

### 2. Configurar la base de datos

Asegúrate de tener MySQL en ejecución. La aplicación creará la base de datos automáticamente si no existe.

En `src/main/resources/application.properties`, ajusta las credenciales si es necesario:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/spr_coches_marcas?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=1234
server.port=9090
```

### 3. Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

La aplicación arrancará en `http://localhost:9090`.

Al iniciarse, el archivo `import.sql` cargará automáticamente datos de prueba: marcas (Toyota, BMW, Ford), coches y dos usuarios predefinidos.

---

## 🔐 Autenticación JWT

La API utiliza autenticación **stateless** con tokens JWT. Para acceder a los endpoints protegidos:

**1. Obtener token:**

```http
POST /api/v1/usuarios/ValidarUsuario
Content-Type: application/json

{
  "user": "admin",
  "pass": "1234"
}
```

**Respuesta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "user": "admin"
}
```

**2. Usar el token en las peticiones:**

```http
Authorization: Bearer <token>
```

### Usuarios de prueba

| Usuario | Contraseña | Rol |
|---|---|---|
| `admin` | `1234` | ADMIN |
| `user` | `1234` | USER |

---

## 📡 Endpoints de la API

### 🏷️ Marcas — `/api/v1/marcas`

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/v1/marcas/` | Listar todas las marcas |
| GET | `/api/v1/marcas/{id}` | Obtener marca por ID |
| POST | `/api/v1/marcas/` | Crear nueva marca |
| PUT | `/api/v1/marcas/` | Modificar marca existente |
| DELETE | `/api/v1/marcas/{id}` | Eliminar marca por ID |

### 🚗 Coches — `/api/v1/coches`

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/v1/coches` | Listar todos los coches |
| GET | `/api/v1/coches/ordenados` | Listar coches ordenados por precio |
| POST | `/api/v1/coches` | Crear nuevo coche |
| PUT | `/api/v1/coches/{id}` | Modificar coche por ID |
| DELETE | `/api/v1/coches/{id}` | Eliminar coche por ID |

### 👤 Usuarios — `/api/v1/usuarios`

| Método | Endpoint | Acceso | Descripción |
|---|---|---|---|
| POST | `/api/v1/usuarios/ValidarUsuario` | Público | Login y obtención de token |
| GET | `/api/v1/usuarios/**` | ADMIN | Consultar usuarios |
| POST | `/api/v1/usuarios/` | ADMIN | Crear usuario |
| PUT | `/api/v1/usuarios/**` | ADMIN | Modificar usuario |
| DELETE | `/api/v1/usuarios/**` | ADMIN | Eliminar usuario |

---

## 📖 Documentación Swagger

Una vez iniciada la aplicación, accede a la documentación interactiva en:

```
http://localhost:9090/swagger-ui.html
```

Desde ahí puedes explorar y probar todos los endpoints directamente en el navegador.

---

## 🌐 Interfaz Web (Thymeleaf)

La aplicación también incluye una interfaz web con las siguientes rutas:

| Ruta | Descripción |
|---|---|
| `/` | Página principal |
| `/login` | Formulario de login |
| `/dashboard` | Panel de control |
| `/marcas` | Gestión de marcas |
| `/coches` | Gestión de coches |

---

## 🔒 Seguridad

- Contraseñas almacenadas con **hash SHA-256** usando estrategia **Salt & Pepper**.
- Autenticación completamente **stateless** (sin sesiones en servidor).
- Filtro JWT personalizado aplicado antes del filtro de autenticación estándar de Spring.
- CSRF deshabilitado (apropiado para APIs REST).

---

## 📬 Contacto

<p>
  <a href="https://www.linkedin.com/in/valentina-castillo-191863202/" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-Valentina%20Castillo-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn"/>
  </a>
  &nbsp;
  <a href="https://valentina-castillo.github.io/" target="_blank">
    <img src="https://img.shields.io/badge/Portfolio-valentina--castillo.github.io-FF6B6B?style=for-the-badge&logo=github&logoColor=white" alt="Portfolio"/>
  </a>
</p>
