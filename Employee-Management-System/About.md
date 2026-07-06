# Employee Management System

A simple RESTful Employee Management System built using **Spring Boot**, **Spring Data JPA**, and **PostgreSQL**. This project demonstrates CRUD operations using a layered architecture (Controller → Service → Repository).

## 🚀 Features

* Add Employee
* Get All Employees
* Get Employee by ID
* Get Employees with Salary Greater Than a Given Amount
* Update Employee
* Delete Employee
* PostgreSQL Integration
* Layered Architecture
* Spring Data JPA Repository

## 🛠️ Tech Stack

* Java 22
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Postman

## 📂 Project Structure

```text id="6evtlr"
src
├── controller
├── service
├── repository
├── entity
└── EmployeeManagementApplication
```

## 📌 API Endpoints

| Method | Endpoint                     | Description                            |
| ------ | ---------------------------- | -------------------------------------- |
| POST   | `/employees`                 | Add Employee                           |
| GET    | `/employees`                 | Get All Employees                      |
| GET    | `/employees/{id}`            | Get Employee by ID                     |
| GET    | `/employees/salary/{salary}` | Get Employees with Salary Greater Than |
| PUT    | `/employees/{id}`            | Update Employee                        |
| DELETE | `/employees/{id}`            | Delete Employee                        |

## ⚙️ Database Configuration

```properties id="l2f86s"
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_db
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

## ▶️ How to Run

1. Clone the repository.
2. Create a PostgreSQL database named `employee_db`.
3. Update database credentials in `application.properties`.
4. Run the Spring Boot application.
5. Test the APIs using Postman.

## 📚 Concepts Learned

* REST APIs
* Spring Boot
* Layered Architecture
* Dependency Injection
* Spring Data JPA
* CRUD Operations
* Derived Query Methods (`findBySalaryGreaterThan`)
* PostgreSQL Integration
* Postman API Testing

## 🔮 Future Improvements

* DTOs
* Validation
* Custom Exception Handling
* ResponseEntity
* JPQL & Native Queries
* Spring Security & JWT

## 👨‍💻 Author

**Prashant Kumar**

GitHub: https://github.com/prashantkr81
