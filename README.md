# Spring Boot API Gateway (Read‑Only Proxy)

This is a lightweight **Spring Boot** service that acts as a read‑only proxy (API Gateway) in front of a Node.js “Apps” service. External teams can retrieve app data without direct access to the JSON file or the internal backend.

---

## 🗂 Project Structure

```
gateway/
├── src/
│   ├── main/
│   │   ├── java/com/example/eksdet/
│   │   │   ├── gateway/
│   │   │   │   └── GatewayController.java
│   │   │   └── config/
│   │   │       └── SwaggerConfig.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/eksdet/
│           └── ApiGatewayTest.java
├── pom.xml
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

- **Java 17** or higher  
- **Maven 3.8+**  
- A running **Node.js Apps** service on `http://localhost:3000/apps`

### Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

The gateway starts on **port 8080**.

---

## 📦 Endpoints

| Method | Path                              | Description                         |
|--------|-----------------------------------|-------------------------------------|
| GET    | `/gateway/apps`                   | List all apps (proxy to Node.js)    |
| GET    | `/gateway/apps/{name}`            | Get a single app by name            |
| GET    | `/gateway/apps?owner={ownerName}` | Filter apps by owner                |

---

## 📖 API Documentation

Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

---

## 🧪 Testing

```bash
mvn test
```

Covers basic 200/404 scenarios via Rest Assured.

---

## ⚙️ Configuration

Edit `src/main/resources/application.properties`:

```properties
server.port=8080
node.service.url=http://localhost:3000/apps
```

---

## 🤝 Extending

- Secure with Spring Security (API keys/OAuth)  
- Add circuit breaker (Resilience4j)  
- Introduce caching (Spring Cache)

---

## 📄 License

MIT License
