# Spring Boot Learning Series

This repository is dedicated to daily learning of Spring Boot concepts. It includes hands-on implementations across different modules like **Transaction** and **CQRS**, structured to give practical exposure to key Spring Boot features and integrations.

## Table of Contents

- [Overview](#overview)
- [Transaction Module](#transaction-module)
- [CQRS Module](#cqrs-module)
    - [Setup & Prerequisites](#setup--prerequisites)
    - [Running the Application](#running-the-application)
    - [Module Testing](#module-testing)
        - [Curl Commands for Testing](#curl-commands-for-testing)
    - [Advanced Features](#advanced-features)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

This repository contains modules that showcase how to use Spring Boot for building backend services. It emphasizes modern practices like CQRS (Command Query Responsibility Segregation) and Transaction management. The modules are self-contained, making it easy for developers to learn and experiment.

---

## Transaction Module

> **Status**: Currently under development.

The **Transaction Module** demonstrates handling financial or account-based operations with data consistency. It covers essential concepts of **transaction management** such as commit, rollback, and isolation levels.

---

## CQRS Module

The **CQRS Module** implements the Command Query Responsibility Segregation pattern. It separates write operations (commands) from read operations (queries) for better scalability and performance.

### Setup & Prerequisites

Ensure the following are installed and running:

1. **Docker**:
    - Install Docker from the official [Docker website](https://www.docker.com/get-started).

2. **Required Docker Containers**:
    - **PostgreSQL**: To store command-side data.
    - **Elasticsearch**: For query-side data indexing and retrieval.
    - **Kafka with Zookeeper**: For message-based communication between the Command and Query services. You can use the combined Docker image:
      ```bash
      docker run -d --name kafka-zookeeper --network=host johnnypark/kafka-zookeeper
      ```

---

### Running the Application

Follow these steps to run the CQRS module:

1. **Ensure Docker containers are running**:
    - Start **PostgreSQL**, **Elasticsearch**, and **Kafka with Zookeeper**.

2. **Build the project using Maven**:
   ```bash
   mvn clean install
   ```

3. **Run the CqrsApplication class**:
   ```bash
   mvn spring-boot:run -pl cqrs
   ```

4. **Access the application**:
    * The CQRS service will be available at: `http://localhost:8080`.

### Module Testing

Once the application is up and running, you can test it using the following **curl** commands or any API testing tool like **Postman**.

#### Curl Commands for Testing

1. **Create a Product** (Command Service):
    * POST request to create a new product:
      ```bash
      curl -X POST -H "Content-Type: application/json" -d '{"name":"Test Product","price":10.99,"quantity":100}' http://localhost:8080/products
      ```

2. **Get Product Data** (Query Service using Elasticsearch):
    * GET request to search for products:
      ```bash
      curl http://localhost:8080/products/search?name=Test
      ```

### Advanced Features

This module incorporates the following advanced features:

* **CQRS Pattern**: Clear separation of command (write) and query (read) services.
* **Kafka**: Message-driven architecture for communication between services.
* **Elasticsearch**: Search and query product data efficiently.
* **PostgreSQL**: Relational database to store product information.
