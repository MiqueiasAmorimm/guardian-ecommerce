# Guardian — Catalog Service

Microservice responsible for product catalog management in an event-driven e-commerce system.

## Tech Stack

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- PostgreSQL 16
- Flyway
- Docker
- Lombok

## Architecture

The project follows a layered architecture with clear separation of concerns:

- **domain** → `Product` entity with business rules (rich domain model)
- **application** → use cases representing system actions
- **infrastructure** → JPA repository for database access
- **web** → REST controllers, DTOs, and global error handling

### Technical Decisions

- **UUID** as ID instead of Long — avoids collision between microservices
- **Flyway** instead of ddl-auto — schema version control
- **Rich domain model** — validation rules stay in the entity, not scattered across layers

## How to Run

### Prerequisites
- Docker
- Java 17

### Starting the database

```bash
docker-compose up -d
```

### Running the application

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

The application runs on port `8081`.

## Endpoints

### Create product
```
POST /products
```
```json
{
    "name": "Nike Sneaker",
    "description": "Men's sports sneaker",
    "price": 299.90
}
```
Returns `201 Created` with the created product.

### Get product by ID

GET /products/{id}

Returns `200 OK` with the product or `404 Not Found`.

### List all products

GET /products

Returns `200 OK` with a list of products.

## Validations

- `name` — required, must not be blank
- `description` — required, must not be blank
- `price` — required, must be greater than or equal to zero

Invalid data returns `400 Bad Request` with a detailed message per field.