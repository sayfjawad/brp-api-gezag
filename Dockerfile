# TODO: use image available to haalcentraal
FROM harbor.cicd.s15m.nl/docker-hub-proxy/eclipse-temurin:17-jre
COPY target/gezag-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
