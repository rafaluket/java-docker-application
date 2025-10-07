# Java Springboot Application with Docker (Postgres DB)
This project is to deploy a springboot api using Docker Container
# How to Use
## 1. Docker Install
- Go to Docker Official Site (https://www.docker.com) and download the version of your OS.
- After the installation you can run the command ``docker run hello-world``. It's a test image to check if docker is working correctly.
## 2. Download the repository
After download it you should go to the root folder via cmd and run the command ``docker-compose up -d``.
This will initiate the docker containers (application + database).

## 3. TEST TIME 🔥
```shell
curl --request GET \
  --url 'http://localhost:8080/product?productId=100'
```
do a GET request with ``productId`` as parameter.

# How it works
## Dockerfile
```shell
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
```
This file will define the startup of our application.
The explanation:
- `FROM ...` we define the jdk to build our application with java version.
- `WORKDIR` we set the application root folder in the docker container to work with it.
- `COPY` we copy all the files to run our application, like the src folder and gradle files.
- `RUN` the gradlew to build the application in our defined workdir.
- `ARG` we set an argument (a variable) as JAR_FILE from our build.
- `COPY` copy the jar file as `application.jar` for execution.
- `EXPOSE` the port 8080 of the machine to receive application request.
- `ENTRYPOINT` we define the parameters to run in a cmd our application.

## docker-compose.yaml
```yaml
version: '3.8'

services:
  app:
    image: 'docker-spring-boot-postgres:latest'
    build:
      context: .
    container_name: app
    depends_on:
      - db
    environment:
      - POSTGRES_USER=postgres!
      - POSTGRES_PASSWORD=postgres!
      - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/product
      - SPRING_DATASOURCE_USERNAME=postgres!
      - SPRING_DATASOURCE_PASSWORD=postgres!
      - SPRING_JPA_HIBERNATE_DDL_AUTO=update
    ports:
      - "8080:8080"

  db:
    image: postgres:latest
    container_name: db
    environment:
      - POSTGRES_USER=postgres!
      - POSTGRES_PASSWORD=postgres!
      - POSTGRES_DB=product
      - POSTGRES_HOST_AUTH_METHOD=md5
    ports:
      - "5432:5432"
```

The docker-compose file will be a easier way to wake up more than one container by just running the command
`docker-compose up`. We set the images and parameters of the container we want to Run.

Example: we defined our database `db:` with a postgres image in the latest version `image: postgres:latest`,
defined the environment variables with postgres user, password and database to run in the port `5432:5432`.

And here we just set up by a simple form, but we can define access restrictions by creating networks in the container
and set up for public and private access. With this we can create a configuration of access on our database by
private network and only access the containers in this network like our backend, and it keep closed from public access.