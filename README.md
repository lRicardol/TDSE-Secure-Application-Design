# TDSE-Secure-Application-Design

## Enterprise Architecture Workshop

**Secure, Scalable and Cloud-Native Application Deployment**

---

## Author

**Ricardo Ayala**  
Systems Engineering Student  

---

# Project Overview

This project implements a **secure distributed web application** deployed on AWS using a **two-tier architecture**:

- **Apache Server (Frontend)**
- **Spring Boot Server (Backend)**

The system is designed following **enterprise security best practices**, ensuring:

- Secure communication using **TLS (HTTPS)**
- Password protection using **hashing (BCrypt)**
- Separation of concerns (Frontend / Backend)
- Cloud deployment using **AWS EC2**

---

# Architecture Design

## High-Level Architecture

```
User (Browser)
   │
   ▼
HTTPS (TLS)
   │
   ▼
Apache Server (Frontend - Port 443)
   │
   ▼
Async JavaScript (Fetch API)
   │
   ▼
HTTPS (TLS)
   │
   ▼
Spring Boot Server (Backend API - Port 8443)
   │
   ▼
Authentication & Business Logic
```

---

## Key Security Features

| Feature | Description |
|--------|------------|
| TLS Encryption | Secure communication using HTTPS |
| Let's Encrypt | Free SSL certificates |
| Password Hashing | BCrypt encryption |
| Separation of Servers | Apache (Client) + Spring (API) |
| Secure REST API | Protected backend endpoints |
| AWS Deployment | Cloud-based infrastructure |

---

# Project Structure

```
secure-app
│
├── backend (Spring Boot)
│   ├── SecureAppApplication.java
│   ├── config
│   │   └── SecurityConfig.java
│   ├── controller
│   │   └── AuthController.java
│   ├── service
│   │   └── AuthService.java
│   ├── model
│   │   └── User.java
│   ├── repository
│   │   └── UserRepository.java
│   └── util
│       └── PasswordUtil.java
│
├── frontend (Apache)
│   ├── index.html
│   ├── login.html
│   ├── dashboard.html
│   ├── js
│   │   ├── login.js
│   │   └── app.js
│   └── css
│       └── styles.css
│
├── apache
│   └── apache.conf
│
├── docs
│   ├── tls-setup.md
│   ├── apache-setup.md
│   ├── spring-setup.md
│   └── ec2-setup.md
│
├── pom.xml
└── README.md
```

---

# Backend - Spring Boot

## Features

- REST API
- Authentication system
- Password hashing with BCrypt
- JSON-based communication

## Example Endpoint

### Login

```
POST /auth/login
```

### Request

```json
{
  "username": "admin",
  "password": "1234"
}
```

### Response

```json
Login successful
```

---

# Frontend - Apache

## Features

- Static HTML + CSS + JS
- Asynchronous communication using `fetch()`
- Login system
- Dashboard page

## Pages

| Page | Description |
|------|------------|
| index.html | Home page |
| login.html | Login form |
| dashboard.html | Protected page |

---

# Async Communication (Frontend → Backend)

Example (`login.js`):

```javascript
fetch("https://<EC2-IP>:8443/auth/login", {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify({
        username: username,
        password: password
    })
});
```

---

# TLS Configuration (HTTPS)

TLS was implemented using **Let's Encrypt**.

## Apache (Frontend)

```
https://<EC2-IP>
```

## Spring Boot (Backend)

```
https://<EC2-IP>:8443
```

Certificates generated using:

```bash
sudo certbot --apache
```

---

# AWS Deployment

## EC2 Instance

- Amazon Linux 2023
- Ports opened:
  - 80 (HTTP)
  - 443 (HTTPS)
  - 8443 (Spring Boot)

---

## Deployment Steps

### Connect to EC2

```bash
ssh -i key.pem ec2-user@<EC2-IP>
```

---

### Install Dependencies

```bash
sudo yum update -y
sudo yum install java-17-amazon-corretto httpd git -y
```

---

### Run Spring Boot

```bash
mvn clean install
java -jar target/*.jar
```

---

### Configure Apache

```bash
sudo systemctl start httpd
sudo systemctl enable httpd
```

---

### Enable TLS

```bash
sudo certbot --apache
```

---

# Testing the Application

## Frontend

```
https://<EC2-IP>
```

---

## Backend API

```
https://<EC2-IP>:8443/auth/login
```

---

# Security Implementation

## Password Hashing

Passwords are stored using:

```
BCryptPasswordEncoder
```

---

## Spring Security

- CSRF disabled for API usage
- Stateless authentication
- Endpoint protection ready

---

# Screenshots

## Apache Running

```
/images/apache.png
```

## Login Page

```
/images/login.png
```

## Dashboard

```
/images/dashboard.png
```

## AWS Deployment

```
/images/aws.png
```

---

# Technologies Used

- Java 17
- Spring Boot 3
- Spring Security
- Apache HTTP Server
- JavaScript (Fetch API)
- HTML5 / CSS3
- Docker (optional)
- AWS EC2
- Let's Encrypt (Certbot)

---

# Laboratory Requirements Coverage

| Requirement | Status |
|------------|--------|
| Apache Server | x      |
| Spring Backend | x      |
| TLS Encryption | x      |
| Async Client | x      |
| Login System | x      |
| Password Hashing | x      |
| AWS Deployment | x      |
| Let’s Encrypt | x      |

---

# Conclusions

This project demonstrates how to build a **secure enterprise-grade architecture** by combining:

- **Frontend (Apache)**
- **Backend (Spring Boot)**
- **Cloud Infrastructure (AWS)**
- **Security Best Practices (TLS + Hashing)**

It reflects real-world systems used in modern cloud applications.

---

# Repository

```
https://github.com/<your-user>/<your-repo>
```

---

# Final Notes

This lab provides a solid foundation in:

- Secure application design
- Distributed systems architecture
- Cloud deployment
- Web security fundamentals

A stepping stone toward building **production-ready secure systems** 