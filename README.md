# Hotel Review Platform

A microservices-based hotel review and rating platform built with Java and Spring Boot architecture.

## Overview

Hotel Review Platform is a distributed application designed to manage hotel information, user accounts, and ratings/reviews. The system is built using microservices architecture with dedicated services for different business domains.

## Architecture

The project follows a microservices architecture pattern with the following components:

### Core Services

- **User Service** (`userService/`) - Handles user authentication, registration, and account management
- **Hotel Service** (`HotelService/`) - Manages hotel information, details, and listings
- **Rating Service** (`RatingService/`) - Manages ratings, reviews, and feedback for hotels
- **API Gateway** (`ApiGateway/`) - Central entry point for all client requests, handles routing and load balancing
- **Config Server** (`ConfigServer/`) - Centralized configuration management for all microservices

## Technology Stack

- **Language**: Java
- **Framework**: Spring Boot
- **Architecture**: Microservices
- **Build Tool**: Maven/Gradle

## Project Structure
