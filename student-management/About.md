Student Management API

A simple RESTful Student Management API built using Spring Boot, Spring Data JPA, and PostgreSQL. This project demonstrates the fundamentals of backend development, including CRUD operations, REST APIs, dependency injection, and database integration.

🚀 Features

- Create a new student
- Retrieve all students
- Retrieve a student by ID
- Update student details
- Delete a student
- Automatic database table creation using Hibernate
- RESTful API architecture

🛠️ Tech Stack

- Java 21 (or Java 17)
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- Hibernate
- Postman (API Testing)

📁 Project Structure

src
├── main
│   ├── java
│   │   └── com.prashant.student_management
│   │       ├── controller
│   │       ├── entity
│   │       ├── repository
│   │       └── StudentManagementApplication.java
│   └── resources
│       └── application.properties

⚙️ Configuration

Configure your PostgreSQL database in "application.properties":

spring.datasource.url=jdbc:postgresql://localhost:5432/student_db
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

Replace "your_password" with your PostgreSQL password.

▶️ Running the Project

1. Clone the repository.
2. Create a PostgreSQL database named "student_db".
3. Update the database credentials in "application.properties".
4. Run the Spring Boot application.
5. The server starts on:

http://localhost:8080

📌 API Endpoints

Create Student

POST "/students"

Request Body:

{
  "name": "Prashant",
  "email": "prashant@gmail.com",
  "age": 21,
  "course": "B.Tech"
}

---

Get All Students

GET "/students"

---

Get Student by ID

GET "/students/{id}"

Example:

GET /students/1

---

Update Student

PUT "/students/{id}"

Request Body:

{
  "name": "Prashant Kumar",
  "email": "prashantk@gmail.com",
  "age": 22,
  "course": "MCA"
}

---

Delete Student

DELETE "/students/{id}"

Example:

DELETE /students/1

🗄️ Database Schema

Column| Type
id| BIGINT (Primary Key, Auto Increment)
name| VARCHAR
email| VARCHAR
age| INTEGER
course| VARCHAR

📷 Testing

The APIs can be tested using:

- Postman
- Thunder Client
- Insomnia

📚 Concepts Covered

- Spring Boot Project Setup
- REST Controllers
- HTTP Methods (GET, POST, PUT, DELETE)
- Request Mapping
- Path Variables
- Request Body
- JPA Entities
- Spring Data JPA Repository
- Dependency Injection (Constructor Injection)
- PostgreSQL Integration
- Hibernate ORM
- CRUD Operations

🔮 Future Improvements

- Service Layer
- DTOs
- Validation
- Global Exception Handling
- Spring Security
- JWT Authentication
- Swagger/OpenAPI Documentation
- Docker Support
- Unit & Integration Testing

👨‍💻 Author

Prashant Kumar

