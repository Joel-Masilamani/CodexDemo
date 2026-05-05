# Stage 1: Build with Maven and Java 21 (using Eclipse Temurin)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run with Java 21 (using Eclipse Temurin)
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
# Use a wildcard to find the JAR, or use the specific name from your pom.xml
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
