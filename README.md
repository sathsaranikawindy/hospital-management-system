# Enterprise Hospital Management System (Microservices Architecture)

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)
![Spring Cloud Gateway](https://img.shields.io/badge/Spring_Cloud-Gateway-blue)
![MongoDB](https://img.shields.io/badge/MongoDB-Atlas-brightgreen)
![Docker](https://img.shields.io/badge/Docker-Containerized-blue)

An end-to-end distributed microservices system designed and developed for the **Service-Oriented Computing** module coursework. The system orchestrates multiple decoupled domain microservices behind a centralized API Gateway, featuring OAuth 2.0/JWT security, custom API Key filtering, rate limiting, global CORS policies, and a unified client web application.

---

## 1. System Architecture Diagram

                             +-----------------------------------+
                             |    Unified Frontend Client App    |
                             |         (Port 3000 / Web)         |
                             +-----------------+-----------------+
                                               |
                                               | HTTP / REST Requests
                                               v
                             +-----------------------------------+
                             |   Spring Cloud API Gateway        |
                             |         (Port 8080)               |
                             |  - OAuth 2.0 / JWT Verification   |
                             |  - Rate Limiting (Bucket4j)       |
                             |  - Global CORS Policy             |
                             +-----------------+-----------------+
                                               |
   +-------------------------------------------+-------------------------------------------+
   | Forward with                              | Forward with                              | Forward with
   | x-api-key Header                          | x-api-key Header                          | x-api-key Header
   v                                           v                                           v
+-----------------------+                   +-----------------------+                   +-----------------------+|  Auth Microservice    |                   |  Room Microservice    |                   | Booking Microservice  ||     (Port 8081)       |                   |     (Port 8082)       |                   |     (Port 8083)       |+-----------+-----------+                   +-----------+-----------+                   +-----------+-----------+|                                           |                                           |v                                           v                                           v+-----------------------+                   +-----------------------+                   +-----------------------+|     MongoDB Atlas     |                   |     MongoDB Atlas     |                   |     MongoDB Atlas     ||      (auth_db)        |                   |      (hotel_db)       |                   |     (booking_db)      |+-----------------------+                   +-----------------------+                   +-----------------------+
---

## 2. Work Breakdown & Repository Ownership Matrix

| Student Name | Student ID | System Role | Microservice Name | Key Responsibilities & Endpoints |
| :--- | :--- | :--- | :--- | :--- |
| **Y.K. Sathsarani** | ITBIN2313-0133 | Group Lead / Gateway | Auth & API Gateway Service | API Gateway setup, OAuth 2.0 / JWT issuance, Rate Limiting, CORS, `/auth/login`, `/auth/register` |
| **[Student 2 Name]** | ITBIN2313-XXXX | Backend Developer | Room Management Service | Spring Boot API, API Key Auth, MongoDB Integration, `/rooms`, `/rooms/{id}`, `/rooms/available` |
| **W. Chamudhi Lakmindi** | ITBIN2313-0126 | Backend Developer | Booking & Reservation Service | Spring Boot API, API Key Auth, MongoDB Integration, `/bookings`, `/bookings/checkout`, `/bookings/history` |
| **[Student 4 Name]** | ITBIN2313-XXXX | Backend / Frontend Lead | Notification Service & Client | API Key Auth, Email/SMS notification dispatch, Web Client Integration, `/notify/email`, `/notify/sms` |
| **[Student 5 Name]** | ITBIN2313-XXXX | Backend Developer | Payment Service | API Key Auth, Payment processing endpoints, `/payments/process`, `/payments/history` |

---

## 3. Prerequisites & Environment Setup

Before running the application, ensure you have the following tools installed:

* **Docker Desktop** (v20.10 or higher) with Docker Compose enabled
* **Git**
* **MongoDB Compass** (Optional, for database inspection)
* **Java Development Kit (JDK 17)** & **Maven** (Optional, for local non-containerized running)

---

## 4. How to Run the System via Docker

The entire microservices ecosystem (all Spring Boot services, API Gateway, and Frontend Client) is fully containerized and orchestrated via Docker.

1. **Clone the Repository:**
   ```bash
   git clone [https://github.com/your-username/hotel-management-system.git](https://github.com/your-username/hotel-management-system.git)
   cd hotel-management-system
Spin Up All Services:Run the following single command in the project root directory:Bashdocker compose up --build -d
Verify Running Containers:Bashdocker compose ps
Stop the System:Bashdocker compose down
5. Security Credentials & API Header FormatsIndividual Microservice API Key SecurityEach downstream microservice enforces direct protection using an internal API Key filter. Direct unauthorized calls without the valid header will return 401 Unauthorized.Header Key Name: x-api-keyTest Dev API Key: SUPER-SECRET-DEV-KEY-123Authentication CredentialsTo acquire a JWT Bearer Token via the API Gateway, send a POST request to http://localhost:8080/auth/login:Username: adminPassword: Admin123456. Interactive API Documentation (Swagger UI Access URLs)Every microservice exposes its interactive OpenAPI 3.0 documentation using Swagger UI:Service NameLocal Base URLSwagger UI URLAPI Gatewayhttp://localhost:8080http://localhost:8080/swagger-ui.htmlAuth Servicehttp://localhost:8081http://localhost:8081/swagger-ui.htmlRoom Servicehttp://localhost:8082http://localhost:8082/swagger-ui.htmlBooking Servicehttp://localhost:8083http://localhost:8083/swagger-ui.htmlNotification Servicehttp://localhost:8084http://localhost:8084/swagger-ui.htmlPayment Servicehttp://localhost:8085http://localhost:8085/swagger-ui.html7. Database Verification (MongoDB Atlas)All microservices connect to MongoDB Atlas database instances:auth_db: Stores user credentials and active API keys   hotel_db: Stores room listings, prices, and availability   booking_db: Stores customer reservation logs and checkout history   Verification via MongoDB Compass:
Connect using the cluster connection string to view updated documents after executing requests in Swagger UI.   Plaintextmongodb+srv://sathsaranikawindya02_db_user:Admin12345@cluster0.tjfeoww.mongodb.net/
