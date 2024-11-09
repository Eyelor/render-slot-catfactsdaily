# Use Maven to build the app (with JDK 17)
FROM maven:3.8.6-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and the source code into the container
COPY pom.xml .
COPY src ./src

# Run Maven tests
RUN mvn clean test

# If tests pass, build the JAR file
RUN mvn clean package -DskipTests

# Now, use an OpenJDK base image to run the app
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build image
COPY --from=build /app/target/catfactsdaily-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app will run on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

