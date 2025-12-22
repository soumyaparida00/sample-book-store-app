# Multi-stage build: compile the service, then run on a slim JRE with dockerize wait.
FROM maven:3.9-eclipse-temurin-11 AS builder
WORKDIR /workspace
COPY . .
RUN mvn -pl bookstore-catalog-service -am -DskipTests clean package

FROM eclipse-temurin:11-jre-jammy
VOLUME /tmp
WORKDIR /app

COPY --from=builder /workspace/bookstore-catalog-service/target/bookstore-catalog-service-0.0.1-SNAPSHOT.jar catalog-service.jar
# Using Dockerize to wait for MySQL before starting the service.
COPY bookstore-catalog-service/dockerize /dockerize
RUN chmod +x /dockerize
# Seed catalog images into the container.
COPY images /images

CMD /dockerize -wait tcp://bookstore-mysql-db:3306 -timeout 15m java -Djava.security.egd=file:/dev/./urandom -jar /app/catalog-service.jar
