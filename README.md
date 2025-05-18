
Currency Converter Spring Boot App 🔥🔥🔥

This is a Currency Converter App's Backend that demonstrates service-to-service communication and third-party API integration
The application converts currencies using real-time exchange rates from a free API and log conversions in a PostgreSQL database.

The Architecture
The project contains two microservices:

1. Rate-service
    - This device Fetches exchange rates from a third-party API
    - Caches the response and serves rates to other services


2. The main-service
- Accepts conversion requests
- Fetches exchange rates from `rate-service`
- Logs the request and response in PostgresSQL

How to Run with Docker Compose

Make sure Docker is installed
Also
Docker Compose Should be installed

How to Build and Run the Application

Run this in the project root (where `docker-compose.yml` is located):

```bash
docker-compose up --build