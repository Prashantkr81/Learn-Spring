# 🎓 Student Management API

A simple **Student Management REST API** built using **Spring Boot**, **Spring Data JPA**, and **PostgreSQL**. This project demonstrates the fundamentals of backend development, including RESTful APIs, CRUD operations, dependency injection, and database integration.

---

## 🚀 Features

* ➕ Add a new student
* 📋 Retrieve all students
* 🔍 Retrieve a student by ID
* ✏️ Update student details
* ❌ Delete a student
* 🗄️ Automatic table creation using Hibernate
* 🌐 RESTful API architecture

---

## 🛠️ Tech Stack

* **Java 21**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate**
* **PostgreSQL**
* **Maven**
* **Postman** (API Testing)

---

## 📁 Project Structure

```text
student-management
│── src
│   ├── main
│   │   ├── java
│   │   │   └── com.prashant.student_management
│   │   │       ├── controller
│   │   │       ├── entity
│   │   │       ├── repository
│   │   │       └── StudentManagementApplication.java
│   │   └── resources
│   │       └── application.properties
│── pom.xml
```

---

## ⚙️ Configuration

Update your **application.properties** file with your PostgreSQL credentials.

```properties
spring.application.name=student-management

spring.datasource.url=jdbc:postgresql://localhost:5432/student_db
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

> Replace `your_password` with your PostgreSQL password.

---

## ▶️ Running the Application

1. Clone the repository.

```bash
git clone https://github.com/your-username/student-management.git
```

2. Navigate to the project directory.

```bash
cd student-management
```

3. Create a PostgreSQL database named:

```text
student_db
```

4. Update the database credentials in `application.properties`.

5. Run the Spring Boot application.

6. Open your browser or Postman:

```text
http://localhost:8080
```

---

## 📌 API Endpoints

### 1️⃣ Create Student

**POST** `/students`

#### Request Body

```json
{
  "name": "Prashant",
  "email": "prashant@gmail.com",
  "age": 21,
  "course": "B.Tech"
}
```

#### Response

```json
{
  "id": 1,
  "name": "Prashant",
  "email": "prashant@gmail.com",
  "age": 21,
  "course": "B.Tech"
}
```

---

### 2️⃣ Get All Students

**GET** `/students`

#### Response

```json
[
  {
    "id": 1,
    "name": "Prashant",
    "email": "prashant@gmail.com",
    "age": 21,
    "course": "B.Tech"
  }
]
```

---

### 3️⃣ Get Student By ID

**GET** `/students/{id}`

Example:

```http
GET /students/1
```

---

### 4️⃣ Update Student

**PUT** `/students/{id}`

#### Request Body

```json
{
  "name": "Prashant Kumar",
  "email": "prashantk@gmail.com",
  "age": 22,
  "course": "MCA"
}
```

---

### 5️⃣ Delete Student

**DELETE** `/students/{id}`

Example:

```http
DELETE /students/1
```

---

## 🗄️ Database Schema

| Column | Type    | Description                  |
| ------ | ------- | ---------------------------- |
| id     | BIGINT  | Primary Key (Auto Increment) |
| name   | VARCHAR | Student Name                 |
| email  | VARCHAR | Student Email                |
| age    | INTEGER | Student Age                  |
| course | VARCHAR | Student Course               |

---

## 🧪 Testing

You can test the APIs using:

* ✅ Postman
* ✅ Thunder Client
* ✅ Insomnia

---

## 📚 Concepts Covered

* Spring Boot Fundamentals
* REST API Development
* CRUD Operations
* Spring MVC
* Dependency Injection
* Spring Data JPA
* Hibernate ORM
* PostgreSQL Integration
* Entity Mapping
* Constructor Injection
* JSON Serialization & Deserialization
* HTTP Methods (GET, POST, PUT, DELETE)

---

## 🔮 Future Improvements

* Service Layer
* DTO Pattern
* Validation (`@Valid`)
* Global Exception Handling
* Spring Security
* JWT Authentication
* Swagger / OpenAPI Documentation
* Docker Support
* Unit Testing
* Integration Testing

---

## 👨‍💻 Author

**Prashant Kumar**

