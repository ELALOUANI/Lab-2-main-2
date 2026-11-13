# Lab 2 - Products REST API

A Spring Boot 3 REST API for managing products. Data is kept in memory and is reset whenever the application restarts; this lab does not use a database.

## Requirements

- Java 21
- Maven 3.6.3 or newer

## Run

```shell
mvn spring-boot:run
```

The API is available at `http://localhost:8080`. Run the tests with:

```shell
mvn test
```

## Product endpoints

| Method | Path | Success response |
| --- | --- | --- |
| `POST` | `/api/products` | `201 Created`, product body and `Location` header |
| `GET` | `/api/products` | `200 OK`, array of products |
| `GET` | `/api/products/{id}` | `200 OK`, product body |
| `PUT` | `/api/products/{id}` | `200 OK`, replaced product body |
| `DELETE` | `/api/products/{id}` | `204 No Content` |

Unknown IDs return `404 Not Found`. Invalid requests return `400 Bad Request` with field-specific details in `validationErrors`.

### Request body

`name` must not be blank, `price` must be greater than zero, and `quantity` must be zero or greater. `description` is optional.

```json
{
  "name": "Keyboard",
  "description": "Mechanical keyboard",
  "price": 49.99,
  "quantity": 8
}
```

An importable Postman collection is available at `postman/Products API.postman_collection.json`. Set its `baseUrl` variable to the address of the running API if needed.