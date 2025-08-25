# springspark

A simple Spring Boot web application that connects to Apache Spark running on YARN, providing a web portal for executing Spark SQL queries.

## Features

- HTTPS (TLS) using a PKCS12 keystore
- Displays Spark version at `/spark-version`
- Home page (`/`) acts as a Spark SQL query portal
  - Enter SQL queries and view the first 20 results
- Minimal, clean front-end (Thymeleaf templates)

---

## Prerequisites

- Java 8+
- Maven
- Access to a Spark cluster (YARN)

---

## Getting Started

### 1. Clone the repository

```
git clone https://github.com/joemontenaro/springspark.git
cd springspark
```

### 2. Create a PKCS12 Keystore (for HTTPS)
```
keytool -genkeypair \
  -alias springspark \
  -keyalg RSA \
  -keysize 2048 \
  -storetype PKCS12 \
  -keystore src/main/resources/springspark.p12 \
  -validity 365 \
  -dname "CN=localhost" \
  -storepass changeit \
  -keypass changeit
```
> Update `application.properties` if you use different passwords.

### 3. Build the application
```
mvn clean package
```

### 4. Run the application
```
spark3-submit \
  --master yarn \
  --deploy-mode client \
  --class com.example.springspark.Application \
  target/spring-spark-app-1.0.0.jar
```

### 5. Access the web app
- Home / SQL portal: https://localhost:9443/
- Spark version: https://localhost:9443/spark-version

---

### Configuration
```
server.port=9443
server.ssl.key-store=classpath:springspark.p12
server.ssl.key-store-password=changeit
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=springspark
```

---

### Notes
- Queries entered on the home page are executed via SparkSession on YARN.
- Results show the first 20 rows for readability.
