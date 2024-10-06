# Use a base image for Java
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Maven/Gradle built JAR to the container
COPY target/library-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (or the port your application runs on)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]