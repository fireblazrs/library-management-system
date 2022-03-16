FROM openjdk:17
COPY ./target/library-system-0.0.1-SNAPSHOT.jar /src/
WORKDIR /src/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "library-system-0.0.1-SNAPSHOT.jar"]
