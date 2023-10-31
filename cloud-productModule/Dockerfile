FROM openjdk:17

RUN mkdir -p /app

ADD target/cloud-productModule-1.0-SNAPSHOT.jar /app/

EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app/cloud-productModule-1.0-SNAPSHOT.jar", "--spring.profiles.active=prod"]