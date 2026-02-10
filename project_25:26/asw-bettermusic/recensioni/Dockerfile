FROM eclipse-temurin:21-jdk

# Install the application binary
ADD build/libs/recensioni.jar recensioni.jar

EXPOSE 8080

# Launch the Java application
ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "recensioni.jar"]