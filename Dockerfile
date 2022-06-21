FROM openjdk:11
MAINTAINER Rolando Rodríguez
VOLUME /tmp
ARG JAR_FILE
COPY target/${JAR_FILE} /tmp/veronica-notifications-api.jar
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/tmp/veronica-notifications-api.jar"]