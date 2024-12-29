FROM openjdk:23
RUN mkdir -p /app/
ADD build/libs/auth_project-0.0.1-SNAPSHOT.jar /app/auth_project-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/auth_project-0.0.1-SNAPSHOT.jar"]