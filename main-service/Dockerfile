FROM eclipse-temurin:21-jdk-alpine

LABEL maintainer="joel.irungu00@gmail.com"

WORKDIR /app

COPY target/main-service-0.0.1-SNAPSHOT.jar main-service-app.jar

ENTRYPOINT ["java", "-jar", "main-service-app.jar"]
