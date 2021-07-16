# Use the official maven/Java 8 image to create a build artifact.
# https://hub.docker.com/_/maven
FROM maven:3.6-jdk-8-alpine as builder

# Copy local code to the container image.
WORKDIR /DeepForestRunner

COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn clean package -DskipTests

# Use the Official OpenJDK image for a lean production stage of our multi-stage build.
# https://hub.docker.com/_/openjdk
# https://docs.docker.com/develop/develop-images/multistage-build/#use-multi-stage-builds
FROM openjdk:8-jre-alpine

# Copy the jar to the production image from the builder stage.
COPY --from=builder /DeepForestRunner/target/DeepForestRunner-*.jar /DeepForestRunner.jar

## Service must listen to $PORT environment variable.
## This default value facilitates local development.
ENV PORT 8080

EXPOSE 8080

ENV TZ Asia/Omsk

# Run the web service on container startup.
CMD ["java","-Dserver.port=${PORT}","-jar","/DeepForestRunner.jar"]
