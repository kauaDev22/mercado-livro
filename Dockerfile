# Use the official OpenJDK image as the base image
FROM openjdk:8-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle Wrapper files
COPY gradlew .
COPY gradle gradle

# Copy the project files into the container
COPY . .

# Build the project with the Gradle Wrapper
RUN ./gradlew build

# Set the startup command to run the application
CMD ["java", "-jar", "build/libs/mercado-livro-0.0.1-SNAPSHOT.jar"]


