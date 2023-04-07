# Stage 1: Build the application using Maven
FROM maven:3.8.3-openjdk-17-slim AS build

WORKDIR /app

ENV JAVA_HOME /usr/local/openjdk-17

COPY .mvn .mvn
COPY src src
COPY pom.xml .

RUN mvn clean install -DskipTests=true
RUN mv /app/target/*.jar /app/app.jar

# Stage 2: run the application
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/app.jar .

EXPOSE 3000

CMD ["java", "-jar", "app.jar"]
