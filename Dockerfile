FROM openjdk:17-alpine
COPY ./build/libs/HDmedi_Server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]