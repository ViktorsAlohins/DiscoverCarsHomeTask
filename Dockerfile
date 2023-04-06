# Stage 1: Build the application using Maven
FROM maven:3.8.3-openjdk-17-slim AS build

WORKDIR /app

ENV JAVA_HOME /usr/local/openjdk-17

COPY mvnw .
COPY .mvn .mvn
COPY entrypoint.sh .

RUN chmod +x ./mvnw
RUN chmod +x ./entrypoint.sh

COPY src src
COPY pom.xml .

RUN ./entrypoint.sh

# Stage 2: run the application
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/DiscoverCarsHomeTask-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "DiscoverCarsHomeTask-0.0.1-SNAPSHOT.jar"]

EXPOSE 3000
