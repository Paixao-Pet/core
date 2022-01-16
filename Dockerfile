FROM openjdk:17-jdk-alpine AS build

WORKDIR /app

COPY . /app

RUN ./gradlew clean build

FROM openjdk:17-jdk-alpine

VOLUME /tmp

COPY --from=build /app/build/libs/*SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]
