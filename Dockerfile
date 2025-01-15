FROM openjdk:11
WORKDIR /app
EXPOSE 8081
COPY sgp-authuser-0.0.1-SNAPSHOT.jar /app/sgp-authuser.jar
CMD ["java", "-jar", "/app/sgp-authuser.jar"]