# Catálogo de Libros

Este proyecto es un catálogo de libros que consume la API de Gutendex y persiste los datos en una base de datos PostgreSQL.

## Requisitos

- Java 17
- PostgreSQL
- Maven

## Configuración

1. **Configurar la base de datos PostgreSQL**:
   - Crea una base de datos llamada `librosdb`.
   - Configura el usuario `postgres` con la contraseña `123456`.

2. **Configurar el archivo `application.properties`**:
   - Asegúrate de que las propiedades de la base de datos estén correctamente configuradas en `src/main/resources/application.properties`.

## Ejecución

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/tu-repo/literatura.git
   cd literatura
