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

# Stage 2: build the Nginx reverse proxy
FROM nginx:stable AS proxy

COPY nginx.conf /etc/nginx/conf.d/default.conf

# Stage 3: run the application and Nginx
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/DiscoverCarsHomeTask-0.0.1-SNAPSHOT.jar .

# Copy the Nginx configuration file from the proxy build stage
COPY --from=proxy /etc/nginx/conf.d/default.conf /etc/nginx/conf.d/default.conf

# Expose ports 3000 and 80 for the application and Nginx respectively
EXPOSE 3000 80

# Start the application and Nginx
CMD ["java", "-jar", "DiscoverCarsHomeTask-0.0.1-SNAPSHOT.jar"]
