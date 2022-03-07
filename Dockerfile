FROM openjdk:17
ENV PORT 8080
COPY /target/Movie-Characters-API-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

CMD ["-b", "0.0.0.0"]