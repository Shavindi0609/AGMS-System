# Agricultural Management System (AGMS) - Microservices

This repository contains the backend implementation for the **Agricultural Management System (AGMS)**, a distributed system built using **Spring Cloud Microservices Architecture**. The system is designed to monitor plantation zones, track real-time sensor data, and manage crop inventory with automated alerting.

## 🏗️ Architectural Overview
The system follows a decentralized approach where each domain logic is encapsulated in its own service.

* **API Gateway (Port 8080):** The entry point of the system that routes requests and handles JWT security filtering.
* **Identity Service (Port 9896):** Manages user authentication, registration, and JWT token generation.
* **Zone Service (Port 8081):** Handles plantation zones and IoT device registrations.
* **Sensor Service (Port 8082):** Periodically fetches telemetry data from external IoT devices.
* **Automation Service (Port 8083):** Processes sensor data to trigger real-time actions (e.g., Turning on fans).
* **Crop Service (Port 8084):** A CRUD-based service for managing crop cycles and inventory.
* **Infrastructure:** Includes **Eureka Server (Service Discovery)** and **Config Server** for centralized configuration.

---

## 🚀 Getting Started

### 1. Prerequisites
* Java 17 (JDK)
* Maven 3.x
* MySQL Server (Ensure it's running on localhost:3306)

### 2. Startup Sequence (Recommended)
To ensure the service mesh initializes correctly, start the services in the following order:

1.  **Eureka Server:** Start the `eureka-server` and verify the dashboard at `http://localhost:8761`.
2.  **Config Server:** Start the `config-server` (Port 8888).
3.  **API Gateway:** Start the `api-gateway` (Port 8080).
4.  **Identity Service:** Start `identity-service` to enable authentication.
5.  **Domain Services:** Start `zone-service`, `sensor-service`, `automation-service`, and `crop-service`.

---

## 🛠️ API Documentation & Testing

### Postman Collection
The `AGMS_Collection.json` file is located in the root directory. Import it into Postman to test all endpoints.

**Authentication Workflow:**
1.  Use `POST /auth/token` with valid credentials to receive a JWT.
2.  Copy the token and set it as a **Bearer Token** in the Authorization tab for all other requests.

### Service Status
All services must show an **UP** status on the Eureka dashboard before testing inter-service communication.

---

## 💾 Database Details
The system uses MySQL with automated schema creation. Each service targets its own database:
* `agms_identity_db`
* `agms_zone_db`
* `agms_crop_db`

---

## 📸 Screenshots
Screenshots of the fully operational service mesh can be found in the `/docs` folder.

* **Eureka Status:** Registered services showing as UP.
* **Postman Result:** Successful CRUD operations via the Gateway.

---
Developed as part of the Microservices Assignment.
