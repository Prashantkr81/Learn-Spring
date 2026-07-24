# 💰 Finance Management API

A production-style Spring Boot REST API for managing personal financial transactions. This project demonstrates multiple approaches to database interaction using Spring Boot and PostgreSQL, making it an excellent learning project for understanding the Spring Data ecosystem.

---

# 📌 Project Objective

The primary goal of this application is to understand how Spring Boot communicates with a database using different techniques.

Instead of stopping at basic CRUD operations, this project explores multiple querying approaches and compares them, helping developers understand what happens behind the scenes.

---

# 🚀 Features

* Create a transaction
* View all transactions
* Get transaction by ID
* Update transaction
* Delete transaction
* Filter transactions by category
* Filter transactions by type
* Filter by amount
* Search transactions
* Global exception handling
* Input validation
* DTO-based architecture
* Multiple database querying techniques

---

# 🛠️ Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* JdbcTemplate
* Maven
* Lombok

---

# 📂 Project Structure

```text
src
└── main
    └── java
        └── com.example.finance
            ├── controller
            ├── dto
            ├── entity
            ├── enums
            ├── exception
            ├── jdbc
            ├── mapper
            ├── repository
            └── service
```

---

# 🏗️ Architecture

```text
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository / JdbcTemplate
   │
   ▼
Hibernate
   │
   ▼
PostgreSQL
```

Data returned to the client follows this flow:

```text
Database
   │
   ▼
Entity
   │
   ▼
Mapper
   │
   ▼
Response DTO
   │
   ▼
Client
```

---

# 📦 Entity

### Transaction

Fields

* id
* title
* amount
* category
* type (INCOME / EXPENSE)
* date
* description

---

# 📄 DTOs

The project uses separate Request and Response DTOs instead of exposing entities directly.

### Why DTOs?

* Prevent exposing database structure
* Send only required fields
* Improve API security
* Decouple database schema from API responses

Flow:

```text
RequestDTO
      │
      ▼
Entity
      │
      ▼
Database
      │
      ▼
Entity
      │
      ▼
ResponseDTO
```

---

# 🔄 Object Mapping

Manual mapping is used throughout the project.

A dedicated `TransactionMapper` converts:

* RequestDTO → Entity
* Entity → ResponseDTO

This keeps the service layer clean and separates mapping logic from business logic.

---

# 📚 Layer Responsibilities

## Controller

* Handles HTTP requests
* Accepts request data
* Calls the service layer
* Returns HTTP responses

---

## Service

Contains business logic.

Examples:

* Create transaction
* Update transaction
* Delete transaction
* Convert Entity to DTO
* Throw custom exceptions

---

## Repository

Responsible only for database operations.

Examples:

* Save data
* Retrieve data
* Update records
* Delete records
* Execute queries

---

## Mapper

Converts between DTOs and Entities.

---

# ✅ Validation

The application validates incoming requests using Jakarta Validation.

Examples:

* `@NotBlank`
* `@NotNull`
* `@Positive`

The controller uses `@Valid` to automatically validate incoming request bodies.

---

# ⚠️ Exception Handling

A custom `ResourceNotFoundException` is used for missing records.

Global exception handling is implemented using:

```java
@RestControllerAdvice
```

Handled exceptions include:

* Resource not found
* Validation failures

This ensures consistent and user-friendly error responses.

---

# 🗄️ Database Access Techniques

One of the main objectives of this project is to compare different ways of querying a database.

## 1. CRUD Operations

Performed using `JpaRepository`.

Examples:

* save()
* findAll()
* findById()
* deleteById()

---

## 2. Derived Query Methods

Queries generated automatically from method names.

Examples:

```java
findByCategory()

findByType()

findByAmountGreaterThan()

findByAmountBetween()

findByTitleContaining()

findByCategoryAndType()
```

No SQL is written manually.

---

## 3. JPQL (Java Persistence Query Language)

Uses entity names and Java field names instead of database tables.

Example:

```java
@Query("""
SELECT t
FROM Transaction t
WHERE t.category = :category
""")
```

JPQL is useful for custom object-oriented queries.

---

## 4. Native SQL

Executes actual SQL directly against PostgreSQL.

Example:

```java
@Query(
value = """
SELECT *
FROM transactions
WHERE category = :category
""",
nativeQuery = true
)
```

Useful for database-specific queries and complex SQL.

---

## 5. JdbcTemplate

JdbcTemplate provides direct SQL execution without relying on JPA.

A custom `RowMapper` converts database rows into Java objects.

Example flow:

```text
SQL Query
      │
      ▼
JdbcTemplate
      │
      ▼
RowMapper
      │
      ▼
Transaction Object
```

This section demonstrates what happens internally when Spring Data JPA interacts with the database.

---

# 📡 Sample Endpoints

## CRUD

```
POST    /transactions
GET     /transactions
GET     /transactions/{id}
PUT     /transactions/{id}
DELETE  /transactions/{id}
```

---

## Derived Queries

```
GET /transactions/category/{category}

GET /transactions/type/{type}

GET /transactions/amount/greater/{amount}

GET /transactions/amount/between

GET /transactions/search/{keyword}
```

---

## JPQL

```
GET /transactions/jpql/category/{category}

GET /transactions/jpql/amount/{amount}

GET /transactions/jpql/search/{keyword}
```

---

## Native SQL

```
GET /transactions/native/category/{category}

GET /transactions/native/amount/{amount}
```

---

# 🎯 Concepts Learned

* Spring Boot REST APIs
* Layered Architecture
* DTO Pattern
* Manual Object Mapping
* Validation
* Exception Handling
* ResponseEntity
* JpaRepository
* Derived Query Methods
* JPQL
* Native SQL
* JdbcTemplate
* RowMapper
* Hibernate
* PostgreSQL Integration

---

# 📖 Learning Outcome

This project provides a comprehensive understanding of Spring Boot's persistence layer by implementing multiple approaches for database interaction.

Instead of only learning CRUD operations, it demonstrates how Spring Data JPA, Hibernate, JPQL, Native SQL, and JdbcTemplate work together, helping developers understand both high-level abstractions and low-level database communication.

---

# 👨‍💻 Author

**Prashant Kumar**

