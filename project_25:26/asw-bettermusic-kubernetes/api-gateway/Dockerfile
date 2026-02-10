FROM eclipse-temurin:21-jdk

# Install the application binary
ADD build/libs/api-gateway.jar api-gateway.jar

EXPOSE 8080

# Launch the Java application
ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "api-gateway.jar"]