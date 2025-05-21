FROM eclipse-temurin:21-jdk-alpine

LABEL maintainer="joel.irungu00@gmail.com"

WORKDIR /app

COPY target/rate-service-0.0.1-SNAPSHOT.jar rate-service-app.jar

ENTRYPOINT ["java", "-jar", "rate-service-app.jar"]
