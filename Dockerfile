FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar /app/myapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]
