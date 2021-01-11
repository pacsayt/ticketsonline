# pt++ : docker run -p 8761:8761 --name ticketsonline
# docker run -d -p 8080:8080 --name ticketsonline pacsayt/icketsonline -jar
FROM openjdk:13-jdk-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# /var/tmp - is in the host's file system
VOLUME /var/tmp
# /usr/src/ticketsonline ???
WORKDIR /opt/ticketsonline
# EXPOSE 8080 ???
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
# ADD ${JAR_FILE} /opt/ticketsonline ???
# ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java -jar $JAVA_OPTS app.jar" ]
# The array form of the Dockerfile ENTRYPOINT is used
# so that there is no shell wrapping the Java process.