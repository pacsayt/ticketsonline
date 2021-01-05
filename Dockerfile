# pt++ : docker run -p 8761:8761 --name ticketsonline
# docker run -d -p 8080:8080 --name ticketsonline pacsayt/icketsonline -jar
FROM openjdk:13-jdk-alpine
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
