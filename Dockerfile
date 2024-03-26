FROM openjdk:11-jre-slim

COPY build/libs/mercado-livro-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080





CMD ["java", "-jar", "/app/app.jar"]