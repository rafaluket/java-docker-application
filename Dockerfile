FROM gradle:jdk17-alpine AS builder
WORKDIR /app

COPY gradlew .
COPY gradle/ /app/gradle/
COPY build.gradle settings.gradle ./

COPY src /app/src

RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:17-jre-alpine

ARG JAR_FILE=build/libs/*.jar

COPY --from=builder /app/${JAR_FILE} application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]