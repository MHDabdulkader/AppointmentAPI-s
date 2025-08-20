# Appointment Management System
A simple **Spring Boot** application for managing appointments.
The project demonstrates a clean backend architecture with **DTO's, Services, Controller, Repository**.

---

## Features
- Create, update, delete, and fetch appointments
- Uses **DTO's** for clean request/response handling
- Layered architecture:
  - **Controller** : Handles REST API endpoints.
  - **Service / ServiceImpl** : Bussiness logic.
  - **Repository** : Database interaction with JPA
- Custom **API Response wrapper** for consistent responses
- PostgreSQL database integration

---

### Tech Stack
- **Java 17+**
- **Spring Boot** (Web, JPA)
- **Database:** Supabase PostgreSQL (with PgBouncer transaction pooling)
- **Maven** as build tool

---
## Setup and Run
### 1. Clone Repository
```bash 
   git clone  https://github.com/MHDabdulkader/AppointmentAPI-s.git
```
### 2. Configure Supabase Database
This project uses **Supabase PostgreSQL** with the **transaction pooler** enabled.
Update your `application.properties` with your Supabase connection details:
```properties
spring.datasource.url=jdbc:postgresql://<Db server>.pooler.supabase.com:6543/postgres?user=[Your-Username]&password=[Your-Password]
# 3. Hibernate / JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
### 3. Run application
```shell
   mvnw spring-boot:run
   # server will start at http://localhost:8080
```

---

## API Endpoints
- **POST** `/appointments` : Create new appointment.
- **GET** `/appointments/{id}` : Get appointment by ID
- **GET** `/appointments/` : Get all appointments.
- **PUT** `/appointments/{id}` : Update and appointment
- **DELETE** `/appointments/{id}` : Delete an appointment