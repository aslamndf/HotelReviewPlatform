# Hotel Review Platform

A comprehensive microservices-based hotel review and rating platform built with Java and Spring Boot. This distributed system manages hotel information, user accounts, ratings, and reviews with scalability and maintainability in mind.

## 📋 Overview

Hotel Review Platform is a production-ready distributed application designed to manage hotel information, user accounts, and ratings/reviews. The system leverages microservices architecture with dedicated, independent services for different business domains, enabling scalability, resilience, and independent deployment cycles.

## 🏗️ Architecture

The project follows a robust microservices architecture pattern with the following key components:

### Core Microservices

1. **User Service** (`userService/`)
   - Port: 8081
   - **Responsibilities**: User authentication, registration, account management, and profile management
   - **Key Features**:
     - User registration and authentication
     - Account management and profile updates
     - OAuth2/JWT token-based security with Okta integration
     - Feign client enabled for inter-service communication
   - **Technology**: Spring Boot 3.2.12, Java 21, MySQL, Spring Security, Spring Data JPA
   - **Key Packages**:
     - `config/` - Configuration classes for security and application setup
     - `controller/` - REST API endpoints for user operations
     - `exceptions/` - Custom exception handling
     - `impl/` - Business logic implementations
     - `services/` - Service layer for user-related operations
     - `repo/` - Repository interfaces for database access
     - `model/` - Entity and DTO models
     - `payload/` - Request/response payload objects
     - `external/` - External service integrations

2. **Hotel Service** (`HotelService/`)
   - Port: 8082
   - **Responsibilities**: Hotel information management, hotel listings, and staff management
   - **Key Features**:
     - Hotel CRUD operations (Create, Read, Update, Delete)
     - Hotel details and descriptions
     - Staff management and associations
     - Security integration with Okta
     - Database persistence with Spring Data JPA
   - **Technology**: Spring Boot 4.0.1, Java 21, Spring Security, Spring Data JPA, MySQL
   - **Key Packages**:
     - `config/` - Configuration and bean definitions
     - `controller/` - REST endpoints for hotel and staff operations
     - `exceptions/` - Custom exception classes
     - `impl/` - Implementation of service interfaces
     - `service/` - Service layer interfaces and implementations
     - `model/` - Entity classes and DTOs
     - `repo/` - JPA repository interfaces

3. **Rating Service** (`RatingService/`)
   - Port: 8083
   - **Responsibilities**: Ratings, reviews, and feedback management for hotels
   - **Key Features**:
     - Create, read, update, and delete ratings/reviews
     - Rating aggregation and statistics
     - Feedback management
     - Configuration client for centralized settings
   - **Technology**: Spring Boot 4.0.1, Java 21, Spring Security, Spring Data JPA
   - **Key Packages**:
     - `config/` - Configuration management
     - `controller/` - REST API endpoints for rating operations
     - `exceptions/` - Custom exception handling
     - `impli/` - Service implementation classes
     - `service/` - Service interfaces
     - `model/` - Rating and review entity classes
     - `repo/` - Repository interfaces for data access

4. **API Gateway** (`ApiGateway/`)
   - Port: 8084
   - **Responsibilities**: Central entry point, request routing, and load balancing
   - **Key Features**:
     - Spring Cloud Gateway for intelligent routing
     - Service discovery with Eureka client
     - Load balancing across microservices
     - Request/response filtering and modification
     - Dynamic route discovery
   - **Technology**: Spring Boot 2.7.6, Java 17, Spring Cloud Gateway, Spring Cloud Eureka, WebFlux
   - **Routing Configuration**:
     - `/users/**` → USERSERVICE
     - `/hotels/**`, `/staffs/**` → HOTELSERVICE
     - `/ratings/**` → RATINGSERVICE
   - **Key Packages**:
     - `config/` - Gateway configuration and routing rules
     - `controller/` - Gateway endpoints
     - `model/` - Request/response models

5. **Config Server** (`ConfigServer/`)
   - Port: 8085
   - **Responsibilities**: Centralized configuration management for all microservices
   - **Key Features**:
     - Centralized configuration properties
     - Dynamic configuration updates without service restart
     - Service discovery via Eureka
   - **Technology**: Spring Boot 3.2.3, Java 21, Spring Cloud Config Server, Eureka Client
   - **Components**:
     - `ConfigServerApplication.java` - Main Spring Boot application class

## 🛠️ Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Language** | Java | 17-21 |
| **Framework** | Spring Boot | 2.7.6 - 4.0.1 |
| **Cloud** | Spring Cloud | 2021.0.5 - 2025.1.0 |
| **Architecture** | Microservices | - |
| **Build Tool** | Maven | - |
| **Database** | MySQL | - |
| **Security** | OAuth2/JWT (Okta) | - |
| **Service Discovery** | Eureka | - |
| **API Gateway** | Spring Cloud Gateway | - |
| **Inter-Service Communication** | Feign Client / RestTemplate | - |

## 📁 Project Structure

```
HotelReviewPlatform/
├── ApiGateway/
│   └── ApiGateway/
│       ├── pom.xml                          # Maven configuration for API Gateway
│       ├── mvnw / mvnw.cmd                  # Maven wrapper scripts
│       └── src/
│           ├── main/
│           │   ├── java/com/aslam/ApiGateway/
│           │   │   ├── ApiGatewayApplication.java      # Main application class
│           │   │   ├── config/               # Gateway configuration
│           │   │   ├── controller/           # REST endpoints
│           │   │   └── model/                # Data models
│           │   └── resources/
│           │       ├── application.yml       # Gateway configuration (Port 8084)
│           │       └── application.properties
│           └── test/
│               └── java/com/aslam/ApiGateway/
│                   └── ApiGatewayApplicationTests.java
│
├── ConfigServer/
│   └── ConfigServer/
│       ├── pom.xml                          # Maven configuration for Config Server
│       ├── mvnw / mvnw.cmd                  # Maven wrapper scripts
│       └── src/
│           ├── main/
│           │   ├── java/com/aslam/ConfigServer/
│           │   │   └── ConfigServerApplication.java    # Main application class
│           │   └── resources/
│           │       ├── application.yml       # Config server setup (Port 8085)
│           │       └── application.properties
│           └── test/
│               └── java/com/aslam/ConfigServer/
│                   └── ConfigServerApplicationTests.java
│
├── HotelService/
│   └── HotelService/
│       ├── pom.xml                          # Maven configuration for Hotel Service
│       ├── mvnw / mvnw.cmd                  # Maven wrapper scripts
│       └── src/
│           ├── main/
│           │   ├── java/com/aslam/HotelService/
│           │   │   ├── HotelServiceApplication.java    # Main application class
│           │   │   ├── config/               # Spring configuration, security config
│           │   │   ├── controller/           # REST API endpoints
│           │   │   ├── service/              # Business logic interfaces
│           │   │   ├── impl/                 # Service implementation classes
│           │   │   ├── model/                # Entity classes and DTOs
│           │   │   ├── repo/                 # JPA repository interfaces
│           │   │   └── exceptions/           # Custom exception classes
│           │   └── resources/
│           │       ├── application.yml       # Hotel service configuration (Port 8082)
│           │       └── application.properties
│           └── test/
│               └── java/com/aslam/HotelService/
│                   └── HotelServiceApplicationTests.java
│
├── RatingService/
│   └── RatingService/
│       ├── pom.xml                          # Maven configuration for Rating Service
│       ├── mvnw / mvnw.cmd                  # Maven wrapper scripts
│       └── src/
│           ├── main/
│           │   ├── java/com/aslam/RatingService/
│           │   │   ├── RatingServiceApplication.java   # Main application class
│           │   │   ├── config/               # Configuration classes
│           │   │   ├── controller/           # REST API endpoints
│           │   │   ├── service/              # Service interfaces
│           │   │   ├── impli/                # Service implementation (note: 'impli' not 'impl')
│           │   │   ├── model/                # Entity and DTO models
│           │   │   ├── repo/                 # JPA repository interfaces
│           │   │   └── exceptions/           # Custom exception handling
│           │   └── resources/
│           │       ├── application.yml       # Rating service configuration (Port 8083)
│           │       └── application.properties
│           └── test/
│               └── java/com/aslam/RatingService/
│                   └── RatingServiceApplicationTests.java
│
├── userService/
│   └── userService/
│       ├── pom.xml                          # Maven configuration for User Service
│       ├── mvnw / mvnw.cmd                  # Maven wrapper scripts
│       └── src/
│           ├── main/
│           │   ├── java/com/aslam/userService/
│           │   │   ├── UserServiceApplication.java     # Main application class with @EnableFeignClients
│           │   │   ├── config/               # Security and application configuration
│           │   │   ├── controller/           # REST API endpoints
│           │   │   ├── services/             # Business logic services
│           │   │   ├── impl/                 # Service implementation classes
│           │   │   ├── model/                # Entity classes and DTOs
│           │   │   ├── payload/              # Request/response payload classes
│           │   │   ├── repo/                 # Repository interfaces
│           │   │   ├── exceptions/           # Custom exception classes
│           │   │   └── external/             # External service clients (Feign)
│           │   └── resources/
│           │       ├── application.yml       # User service configuration (Port 8081)
│           │       └── application.properties
│           └── test/
│               └── java/com/aslam/userService/
│                   └── (Test classes)
│
├── .git/                                    # Git version control
├── .idea/                                   # IntelliJ IDEA project configuration
└── README.md                                # Project documentation

```

## 🔄 Service Communication Flow

```
Client Request
    ↓
API Gateway (Port 8084)
    ↓
┌─────────────────────────────────┐
│ Service Discovery & Routing     │
└─────────────────────────────────┘
    ├─→ User Service (Port 8081)
    ├─→ Hotel Service (Port 8082)
    └─→ Rating Service (Port 8083)
    
Config Server (Port 8085) provides centralized configuration
```

## 🔐 Security Features

- **OAuth2 Integration**: Okta-based OAuth2 authentication
- **JWT Tokens**: Token-based request authentication
- **Spring Security**: Integrated security framework across all services
- **Role-Based Access Control**: Service-level authorization

## 📊 Database Configuration

All services use **MySQL** with the following configuration:
- **Database**: microservices
- **Default Port**: 3306
- **Default User**: root
- **Hibernate DDL**: update (auto-creates/updates tables)

Connection string: `jdbc:mysql://localhost:3306/microservices`

## 🚀 Getting Started

### Prerequisites

- Java 17+ (Java 21 recommended)
- Maven 3.6+
- MySQL 8.0+
- Spring Boot 2.7.6+

### Build & Run

1. **Build all services**:
   ```bash
   cd HotelReviewPlatform
   mvn clean install
   ```

2. **Start Config Server** (Port 8085):
   ```bash
   cd ConfigServer/ConfigServer
   mvn spring-boot:run
   ```

3. **Start Services** (in any order):
   ```bash
   # Terminal 1: User Service (Port 8081)
   cd userService/userService
   mvn spring-boot:run
   
   # Terminal 2: Hotel Service (Port 8082)
   cd HotelService/HotelService
   mvn spring-boot:run
   
   # Terminal 3: Rating Service (Port 8083)
   cd RatingService/RatingService
   mvn spring-boot:run
   
   # Terminal 4: API Gateway (Port 8084)
   cd ApiGateway/ApiGateway
   mvn spring-boot:run
   ```

## 📌 API Endpoints

### User Service (Port 8081 / Gateway path `/users/**`)
- User registration and authentication
- Profile management
- Account operations

### Hotel Service (Port 8082 / Gateway paths `/hotels/**`, `/staffs/**`)
- Hotel CRUD operations
- Hotel details retrieval
- Staff management

### Rating Service (Port 8083 / Gateway path `/ratings/**`)
- Create and manage ratings
- Retrieve reviews
- Rating statistics

## 📝 Configuration

Each service includes `application.yml` with:
- Service name and port
- Database connection details
- Spring Cloud configuration server integration
- Security/OAuth2 settings
- Service-specific properties

## 🔗 Inter-Service Communication

- **User Service**: Uses Feign Client (`@EnableFeignClients`) for calling other services
- **Feign Configuration**: RestTemplate bean configured for fallback scenarios
- **Load Balancing**: Eureka-based service discovery with client-side load balancing

## 📦 Dependencies

### Common Dependencies Across Services
- Spring Boot Starter Web/WebMVC
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Cloud Config Client
- MySQL Connector/J
- Spring Cloud Netflix Eureka Client
- Okta Spring Boot Starter (User Service)

### API Gateway Specific
- Spring Cloud Gateway
- Spring Boot Starter WebFlux
- Eureka Client

### Config Server Specific
- Spring Cloud Config Server
- Eureka Client

## 🤝 Contributing

Guidelines for contributing to the Hotel Review Platform project can be added here.

## 👨‍💻 Developer Notes

- Services use Maven for build and dependency management
- Maven wrapper (`mvnw`, `mvnw.cmd`) included for version consistency
- Each service is independently deployable
- Configuration is centralized through Config Server
- Services communicate via REST/HTTP with Feign clients
- All services integrated with Spring Cloud ecosystem

