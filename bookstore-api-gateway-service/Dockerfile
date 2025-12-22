# Multi-stage build: compile the gateway module with Maven, then run on a slim JRE.
FROM maven:3.9-eclipse-temurin-11 AS builder
WORKDIR /workspace
COPY . .
RUN mvn -pl bookstore-api-gateway-service -am -DskipTests clean package

FROM eclipse-temurin:11-jre-jammy
VOLUME /tmp
WORKDIR /app
COPY --from=builder /workspace/bookstore-api-gateway-service/target/bookstore-api-gateway-service-0.0.1-SNAPSHOT.jar gateway-server.jar

EXPOSE 8765

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/gateway-server.jar"]
