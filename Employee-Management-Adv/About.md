# Employee Management System (Spring Boot)

A production-style **Employee Management REST API** built using **Spring Boot**, **Spring Data JPA**, and **PostgreSQL**.

Unlike a basic CRUD application, this project focuses on writing clean, layered, and maintainable backend code using industry practices such as **DTOs**, **manual object mapping**, **validation**, **ResponseEntity**, and **global exception handling**.

---

# Features

* Create Employee
* Get All Employees
* Get Employee by ID
* Update Employee
* Delete Employee
* Search Employees by Department
* Search Employees by Salary
* Search Employees by Salary Range
* Search Employees by Name
* Sort Employees by Salary
* Input Validation
* Custom Exception Handling
* Global Exception Handling
* Clean Layered Architecture

---

# Tech Stack

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Maven
* Jakarta Validation

---

# Project Structure

```text
src/main/java
│
├── controller
│      EmployeeController
│
├── service
│      EmployeeService
│
├── repository
│      EmployeeRepository
│
├── entity
│      Employee
│
├── dto
│      EmployeeRequest
│      EmployeeResponse
│
├── exception
│      EmployeeNotFoundException
│      ErrorResponse
│      ValidationErrorResponse
│      GlobalExceptionHandler
│
└── EmployeeManagementApplication
```

---

# Application Architecture

```text
                HTTP Request

                      │

                      ▼

             EmployeeController

                      │

                      ▼

              EmployeeService

                      │

                      ▼

           EmployeeRepository

                      │

                      ▼

                 PostgreSQL
```

Each layer has its own responsibility.

* Controller handles HTTP requests.
* Service contains business logic.
* Repository communicates with the database.
* Entity represents the database table.
* DTOs transfer data between the client and the application.

---

# Employee Entity

The Employee entity represents the `employees` table.

Fields:

* id
* name
* email
* department
* salary
* joiningDate

JPA automatically maps this Java class to a PostgreSQL table using Hibernate.

---

# Why DTO?

One of the biggest improvements in this project is the introduction of **DTOs (Data Transfer Objects).**

## What is a DTO?

DTO stands for **Data Transfer Object**.

Its only responsibility is to transfer data between the client and the server.

It is **not** responsible for storing data inside the database.

---

## Why not expose Entity directly?

Suppose our Employee entity becomes:

```java
public class Employee {

    private Long id;
    private String name;
    private String email;

    private String password;
    private String panNumber;
    private String aadhaarNumber;
}
```

If we return the entity directly:

```java
return employee;
```

The client may receive:

```json
{
    "id":1,
    "name":"Prashant",
    "password":"abc123",
    "panNumber":"ABCDE1234F",
    "aadhaarNumber":"123456789012"
}
```

This exposes sensitive information.

Instead, we return an `EmployeeResponse` object that contains only the fields required by the client.

This keeps the API secure and independent of the database structure.

---

# Request DTO vs Response DTO

## EmployeeRequest

Represents data coming **from the client**.

```text
Browser

↓

EmployeeRequest

↓

Service
```

Used while:

* Creating Employee
* Updating Employee

It does **not** contain the `id` because the database generates it automatically.

---

## EmployeeResponse

Represents data sent **to the client**.

```text
Service

↓

EmployeeResponse

↓

Browser
```

This allows us to decide exactly what the client receives.

---

# Request Flow

One of the most important concepts learned in this project.

```text
Client

↓

JSON Request

↓

EmployeeRequest DTO

↓

Employee Entity

↓

Database

↓

Employee Entity

↓

EmployeeResponse DTO

↓

JSON Response

↓

Client
```

Notice that the Entity never directly communicates with the client.

---

# Object Mapping

## What is Mapping?

Mapping means converting one object into another object.

Example:

```text
EmployeeRequest

↓

Employee

↓

EmployeeResponse
```

Since these are different Java classes, Java cannot automatically convert one into another.

Therefore, we manually copy the values.

Example:

```java
employee.setName(request.getName());
employee.setEmail(request.getEmail());
employee.setDepartment(request.getDepartment());
employee.setSalary(request.getSalary());
```

Similarly,

```java
response.setId(employee.getId());
response.setName(employee.getName());
response.setDepartment(employee.getDepartment());
```

This process is called **Object Mapping**.

---

# Why Manual Mapping?

In this project, mapping is done manually to understand what actually happens internally.

Later, utilities such as:

* BeanUtils.copyProperties()
* MapStruct

can automate this process.

Learning manual mapping first makes it easier to understand what these tools are doing behind the scenes.

---

# Validation

The project validates incoming requests before they reach the Service layer.

Examples:

```java
@NotBlank
```

```java
@Email
```

```java
@Positive
```

The controller uses:

```java
@Valid
```

If validation fails, Spring automatically throws a validation exception.

---

# ResponseEntity

Instead of returning objects directly:

```java
return employee;
```

the project returns:

```java
ResponseEntity<EmployeeResponse>
```

This provides full control over the HTTP response.

Examples:

* 200 OK
* 201 Created
* 204 No Content
* 400 Bad Request
* 404 Not Found

---

# Custom Exception

Instead of returning `null`, the project throws a custom exception:

```java
throw new EmployeeNotFoundException(...)
```

This makes the code cleaner and easier to maintain.

---

# Global Exception Handling

Using:

```java
@ControllerAdvice
```

and

```java
@ExceptionHandler
```

all exceptions are handled from a single location.

Instead of returning stack traces, the API returns meaningful JSON responses.

Example:

```json
{
    "status":404,
    "message":"Employee not found with id : 10",
    "timestamp":"2026-07-07T17:00:00"
}
```

Validation failures also return structured responses.

Example:

```json
{
    "status":400,
    "message":"Validation Failed",
    "timestamp":"2026-07-07T17:00:00",
    "errors":{
        "name":"Name cannot be empty",
        "email":"Invalid email",
        "salary":"Salary must be greater than zero"
    }
}
```

---

# Repository

The project uses Spring Data JPA.

```java
public interface EmployeeRepository
        extends JpaRepository<Employee, Long>
```

Spring automatically generates implementations for CRUD methods.

No implementation class is required.

---

# Derived Query Methods

The project also demonstrates Spring Data JPA Derived Query Methods.

Examples:

```java
findByDepartment()
```

```java
findBySalaryGreaterThan()
```

```java
findByDepartmentAndSalaryGreaterThan()
```

```java
findBySalaryBetween()
```

```java
findByNameContaining()
```

```java
findAllByOrderBySalaryDesc()
```

Spring converts these method names into SQL automatically.

---

# REST Endpoints

| Method | Endpoint                                                  | Description          |
| ------ | --------------------------------------------------------- | -------------------- |
| POST   | `/employees`                                              | Create Employee      |
| GET    | `/employees`                                              | Get All Employees    |
| GET    | `/employees/{id}`                                         | Get Employee By ID   |
| PUT    | `/employees/{id}`                                         | Update Employee      |
| DELETE | `/employees/{id}`                                         | Delete Employee      |
| GET    | `/employees/department/{department}`                      | Search by Department |
| GET    | `/employees/salary?salary=50000`                          | Salary Greater Than  |
| GET    | `/employees/search?keyword=Pra`                           | Search by Name       |
| GET    | `/employees/salary-range?minSalary=30000&maxSalary=80000` | Salary Range         |
| GET    | `/employees/sort/salary`                                  | Sort by Salary       |

---

# Concepts Learned

* Spring Boot Layered Architecture
* REST APIs
* Controller
* Service
* Repository
* Entity
* DTO
* Request DTO
* Response DTO
* Manual Object Mapping
* Spring Data JPA
* CRUD Operations
* Derived Query Methods
* Bean Validation
* ResponseEntity
* HTTP Status Codes
* Custom Exceptions
* Global Exception Handling
* Clean Project Structure

---

# Future Improvements

The next version of this project can include:

* JPQL
* Native SQL Queries
* JdbcTemplate
* BeanUtils.copyProperties()
* MapStruct
* Pagination
* Sorting using Pageable
* Swagger/OpenAPI Documentation
* Unit Testing with JUnit and Mockito
* Docker
* Spring Security
* JWT Authentication

---

# Learning Outcome

This project helped in understanding how a production-style Spring Boot application is structured.

The focus was not only on implementing CRUD operations but also on learning **why** concepts such as DTOs, mapping, validation, `ResponseEntity`, and exception handling are important in real-world backend development.
