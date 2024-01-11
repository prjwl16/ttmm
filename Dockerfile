# Use a base image with Java 16 for building
FROM gradle:jdk16 as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew clean shadowJar

# Use a base image with Java 16 for the final image
FROM adoptopenjdk/openjdk16:alpine-jre
EXPOSE 3000

COPY --from=builder /home/gradle/src/build/libs/server.jar /app/
COPY --from=builder /home/gradle/src/src/main/resources/ /app/
WORKDIR /app
RUN ls -lah

ARG DOCKER_ENV
CMD ["java", "-Xmx5g", "-Xms500m", "-jar", "server.jar", "run", "Application", "-conf", "main/config.json"]
