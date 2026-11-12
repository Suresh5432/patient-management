# Patient Management System

A Java microservices application for patient records, authentication, billing, and event analytics. Services communicate over HTTP, gRPC, and Kafka, and can be run together with Docker Compose.

## Services

| Service | Responsibility | Compose exposure |
| --- | --- | --- |
| `api-gateway` | Public entry point and JWT validation/routing | `http://localhost:4010` (container port 4005) |
| `auth-service` | User authentication and JWT issuance | `http://localhost:4006` |
| `patient-service` | Patient data and persistence | Internal |
| `billing-service` | Billing gRPC service | Internal, gRPC port 9001 |
| `analytics-service` | Consumes patient events from Kafka | Internal |
| `integration-tests` | REST Assured integration test sources | Run separately |

The Compose stack includes PostgreSQL databases for patient and auth data and a single-node Kafka broker. Patient changes are published to Kafka for analytics; patient-service calls billing over gRPC.

## Requirements

- Docker Engine and Docker Compose
- Java 21 and Maven (only needed to build/run modules or integration tests outside Docker)

## Run with Docker Compose

From the repository root:

```bash
docker compose up --build
```

The gateway is available at `http://localhost:4010`; the auth service is also exposed directly at `http://localhost:4006`. Stop the stack with `Ctrl+C`, or run `docker compose down` in another terminal. Compose uses development database credentials and enables a demo auth user; change these settings before using the stack outside local development.

## Build services locally

Each service is an independent Maven project. For example:

```bash
cd patient-service
./mvnw package
```

Repeat for `api-gateway`, `auth-service`, `billing-service`, or `analytics-service`. On Windows, use `mvnw.cmd`. Services that depend on PostgreSQL, Kafka, or another service need those dependencies configured and running; see each module's `src/main/resources` configuration and `compose.yaml`.

Integration test sources are in `integration-tests`. Start the Compose stack first, then run:

```bash
cd integration-tests
mvn test
```

## Configuration

Compose supplies the service connection settings through environment variables, including PostgreSQL URLs and credentials, Kafka bootstrap servers, and billing gRPC address/port. The default Compose credentials (`admin` / `password`) are for local development only. Review `compose.yaml` and each module's `application.properties` or `application.yml` before changing deployment configuration.
