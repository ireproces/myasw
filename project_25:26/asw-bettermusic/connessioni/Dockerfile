FROM eclipse-temurin:21-jdk

# Install the application binary
ADD build/libs/connessioni.jar connessioni.jar

EXPOSE 8080

# Launch the Java application
ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "connessioni.jar"]