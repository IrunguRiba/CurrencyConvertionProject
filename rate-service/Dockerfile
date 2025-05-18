FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
