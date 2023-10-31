FROM openjdk:17

RUN mkdir -p /app/

ADD target/cloud-gateway-1.0-SNAPSHOT.jar /app/

EXPOSE 7573

ENTRYPOINT ["java", "-jar","/app/cloud-gateway-1.0-SNAPSHOT.jar", "--spring.profiles.active=prod"]