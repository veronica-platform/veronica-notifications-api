FROM openjdk:11
MAINTAINER Rolando Rodríguez
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} /tmp/app.jar
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/tmp/app.jar"]