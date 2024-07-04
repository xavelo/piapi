FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y redis-tools
WORKDIR /app
COPY target/*.jar /app/myapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]
