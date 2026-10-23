# Guardian

Microservices e-commerce platform built to practice back-end engineering with Java and Spring Boot: independent services, each with its own database, communicating through REST and Apache Kafka.

> **Status:** work in progress. Catalog and Order services are functional. The Payment service consumes order events and creates a payment for each order; approval is simulated for now (every payment is approved).

## Services

| Service | Responsibility | Port | Database | Status |
|---|---|---|---|---|
| **catalog-service** (this repository) | Product catalog management | 8081 | PostgreSQL (5433) | Functional |
| [order-service](https://github.com/MiqueiasAmorimm/-guardian-order-service) | Order creation and order status | 8082 | PostgreSQL (5434) | Functional |
| [payment-service](https://github.com/MiqueiasAmorimm/guardian-payment-service) | Creates a payment for each order event | 8083 | PostgreSQL (5435) | In progress |

## How the services work together

```
Client ──► order-service ──(REST)──► catalog-service
               │                         (product price)
               │ calculates the order total
               ▼
        Kafka topic: order.created
               │
               ▼
        payment-service (consumer)
```

1. A client creates an order in **order-service**.
2. **order-service** asks **catalog-service** for the product price over HTTP and calculates the order total.
3. **order-service** publishes an `order.created` event to Kafka.
4. **payment-service** consumes the event and creates a payment for the order. Approval is simulated for now, and a repeated event does not create a second payment.

Each service has its own database, so one can change without breaking the others.

## Tech Stack

- Java 17
- Spring Boot 3 (3.2 in catalog-service, 3.5 in order-service and payment-service)
- Spring Data JPA / Hibernate
- PostgreSQL 16
- Flyway (database migrations)
- Apache Kafka
- Docker and Docker Compose
- JUnit 5 and Mockito
- GitHub Actions (CI)
- Swagger / OpenAPI
- Lombok

## Architecture

Each service follows a layered architecture with clear separation of concerns:

- **domain** → entities and business rules
- **application** → use cases representing system actions
- **infrastructure** → persistence and messaging (JPA repositories, Kafka)
- **web** → REST controllers, DTOs, and global error handling

### Technical Decisions

Key decisions (UUID as ID, Flyway migrations, rich domain model, status enums with transition validators, one database per service, Kafka between services) and the reasoning behind them are documented in [`DECISIONS.md`](DECISIONS.md).

## Roadmap

- [x] Catalog service (create, get and list products), with tests and CI
- [x] Order service with status validation
- [x] Kafka communication: `order.created` published by order-service and consumed by payment-service
- [x] Payment creation in payment-service from `order.created` (approval simulated), with protection against duplicate events
- [ ] Unit tests for payment-service
- [ ] Real approve / reject rules in payment-service
- [ ] Payment result events (`payment.approved` / `payment.rejected`) consumed by order-service
- [ ] Authentication and authorization
- [ ] Containerized deployment (Kubernetes / cloud)

## Catalog Service

### Configuration

| Property | Description | Default |
|---|---|---|
| `DB_URL` | Database URL | `jdbc:postgresql://localhost:5433/guardian_catalog` |
| `DB_USERNAME` | Database username | `guardian` |
| `DB_PASSWORD` | Database password | `guardian` |

### How to Run

#### Prerequisites
- Docker
- Java 17

#### Starting the infrastructure

```bash
docker-compose up -d
```

This starts one PostgreSQL instance per service (catalog, order and payment) and the Kafka broker used for asynchronous communication between services.

#### Running the application

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

The application runs on port `8081`.

### Endpoints

#### Create product
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

#### Get product by ID

`GET /products/{id}`

Returns `200 OK` with the product or `404 Not Found`.

#### List products (paginated)

`GET /products?page=0&size=10`

Returns `200 OK` with a page of products (content, total elements, total pages).
Optional parameters: `page` (default 0), `size` (default 20) and `sort` (e.g. `sort=price,asc`).

### Validations

- `name` — required, must not be blank
- `description` — required, must not be blank
- `price` — required, must be greater than or equal to zero

Invalid data returns `400 Bad Request` with a detailed message per field.

### Testing

Unit tests implemented with JUnit 5 and Mockito, covering all use cases:

- `CreateProductUseCase` — product created successfully
- `GetProductByIdUseCase` — product found and not found
- `GetAllProductsUseCase` — returns all products

Run tests:
```bash
./mvnw test
```

### CI

GitHub Actions runs all unit tests automatically on every push to main.

### API Documentation

Swagger UI is available when the application is running:
`http://localhost:8081/swagger-ui/index.html`