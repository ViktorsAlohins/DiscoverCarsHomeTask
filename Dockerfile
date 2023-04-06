FROM openjdk:17-jdk-slim

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn

RUN chmod +x ./mvnw

COPY src src
COPY pom.xml .

RUN ./mvnw clean install -DskipTests

CMD ["java", "-jar", "target/DiscoverCarsHomeTask-0.0.1-SNAPSHOT.jar"]

EXPOSE 3000