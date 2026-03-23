# Spring Boot Setup - Secure Backend Deployment

## Objective

Deploy and run the **Spring Boot backend** that provides secure REST API services for authentication and user management.

This backend is responsible for:

* Handling login and registration
* Securing passwords using hashing
* Serving REST endpoints
* Communicating with Apache (reverse proxy)

---

## Project Structure

```bash
spring-backend/
│
├── src/main/java/com/secureapp
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   └── security/
│
├── src/main/resources
│   └── application.properties
│
└── pom.xml
```

---

## Navigate to Backend

```bash
cd spring-backend
```

---

## Build the Project

Use Maven to compile and package the application:

```bash
mvn clean package
```

Or if using Maven Wrapper:

```bash
./mvnw clean package
```

---

## Generated Artifact

After compilation, a `.jar` file will be created:

```bash
target/secure-application-backend-1.0.0.jar
```

---

## Run the Application

```bash
java -jar target/secure-application-backend-1.0.0.jar
```

---

## Verify Application

Open in browser:

```text
http://<EC2-PUBLIC-IP>:8080/api
```

Or test endpoints:

```text
http://<EC2-PUBLIC-IP>:8080/api/login
http://<EC2-PUBLIC-IP>:8080/api/register
```

---

## Run in Background (Production Mode)

```bash
nohup java -jar target/secure-application-backend-1.0.0.jar > app.log 2>&1 &
```

---

## Check Running Process

```bash
ps aux | grep java
```

---

## API Endpoints

### Login

```http
POST /api/login
```

Body example:

```json
{
  "username": "admin",
  "password": "1234"
}
```

---

### Register

```http
POST /api/register
```

Body example:

```json
{
  "username": "newuser",
  "password": "securepassword"
}
```

---

## Security Implementation

The backend includes:

* Password hashing using BCrypt
* Basic authentication logic
* Secure credential validation

Example:

```java
passwordEncoder.encode(password)
passwordEncoder.matches(raw, hashed)
```

---

## application.properties (example)

```properties
server.port=8080
spring.application.name=secure-app
```

---

## Integration with Apache

Apache acts as a reverse proxy:

```text
Client → Apache → Spring Boot
```

API requests are forwarded via:

```text
/api → http://localhost:8080/api
```

---

## Important Notes

* Ensure port **8080** is open in AWS Security Group
* Keep backend running before starting Apache
* Do not expose backend directly in production (use Apache)

---

## Architecture Role

```text
Frontend (Apache)
        │
        ▼
Spring Boot Backend (API)
        │
        ▼
Authentication Logic
```

---

## Result

Spring Boot backend successfully running and accessible via:

```text
http://localhost:8080
```

And externally through Apache:

```text
https://ec2-84287932443289734.compute.amazonaws.com/api
```

---

## Conclusion

The backend provides a secure, scalable REST API that:

* Handles authentication
* Protects user data
* Integrates with Apache via reverse proxy
* Supports secure HTTPS communication
