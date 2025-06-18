FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/authService-0.0.1-SNAPSHOT.jar /app/authService.jar

EXPOSE ${PORT}

CMD ["java", "-jar", "/app/authService.jar"]