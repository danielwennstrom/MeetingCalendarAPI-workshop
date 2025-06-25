# Meeting Calendar API

This is a Spring Boot REST API that powers the [Meeting Calendar frontend](https://github.com/danielwennstrom/MeetingCalendar-workshop). It allows users to register, authenticate using JWT, and manage meetings. Admin users can additionally manage user accounts. Intended as a student project.

## Features

- User registration and JWT-based authentication
- Role-based access control (`USER`, `ADMIN`)
- Meeting creation and filtering
- Admin-only user updates
- DTO validation using Jakarta Bean Validation
- MariaDB (default), with support for other databases via Spring Data

## Tech Stack

- Java 17+
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA
- MariaDB (default)
- MapStruct
- Lombok
- Maven

---  

## Getting Started

### Prerequisites

- Java 17 or later
- Maven
- A running MariaDB instance

### Clone and Set Up

```bash  
git clone https://github.com/danielwennstrom/MeetingCalendarAPI-workshop.git 
cd MeetingCalendarAPI-workshop
```  

### Configure `application.properties`

The project uses **MariaDB** by default. Make sure your database is running and create one named `meetingcalendar`.

Update `src/main/resources/application.properties`:

```properties  
# Database connection (MariaDB)  
spring.datasource.url=jdbc:mariadb://localhost:3306/meetingcalendar
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
  
# JWT configuration  
jwt.secretKey=your_very_long_secret_key
jwt.expirationMs=86400000
```  

To generate a secure JWT key:

```bash  
openssl rand -base64 64
```  

Alternatively, use an online Base64 encoder.

Requirements

    Algorithm: HS512 (HMAC with SHA-512)  
    Minimum Key Length: 64 bytes (512 bits) before Base64 encoding  
    Encoding: Must be Base64  
### Run the application

```bash  
./mvnw spring-boot:run
```  

App will start on `http://localhost:8080`.
  
---  

## Default Admin User

When the application starts, a default admin is created if none exists:

```  
Username: admin  
Password: L!Bcu4:EHXY;i#2  
```  

You can use this account to log in and access admin-only features. Other test users are also seeded in the database upon first launch.
  
---  

## Switching Databases

To use another database (e.g., PostgreSQL or H2), change the following in `application.properties`:

### PostgreSQL example:

```properties  
spring.datasource.url=jdbc:postgresql://localhost:5432/meetingcalendar
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=your_user
spring.datasource.password=your_password
```  

Add PostgreSQL dependency to `pom.xml`:

```xml  
<dependency>  
  <groupId>org.postgresql</groupId>  <artifactId>postgresql</artifactId></dependency>  
```  
  
---  

## API Endpoints

### Auth

- `POST /auth/register` – Register new users
- `POST /auth/login` – Authenticate and receive JWT

### Meetings

- `GET /meetings/search` – Filter by date range and participant
- `POST /meetings` – Create a new meeting

### Users

- `PUT /user/{id}` – Update user (Admin only)

---  

## Example Payload: Create Meeting

```json  
{  
  "title": "Team Sync",  
  "description": "Weekly meeting",  
  "dateTime": "2025-07-01T09:00:00Z",  
  "level": "TEAM",  
  "creator": {  
    "id": 1,  
    "username": "admin"  
  },  "participantIds": [2, 3]  
}  
```  

## Authorization Header

Authenticated requests must include the token:

```http  
Authorization: Bearer <your_token>  
```  
  
---