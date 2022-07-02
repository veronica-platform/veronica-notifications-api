FROM openjdk:11
MAINTAINER Rolando Rodríguez
LABEL "com.datadoghq.ad.logs"='[{"source": "java"}]'
RUN mkdir /code
ARG JAR_FILE
COPY target/${JAR_FILE} /code/app.jar
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/code/app.jar"]