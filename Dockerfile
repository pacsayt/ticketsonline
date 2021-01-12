# pt++ : docker run -p 8761:8761 --name ticketsonline
# docker run -d -p 8080:8080 --name ticketsonline pacsayt/icketsonline -jar
 # image 1.) build_ticketsonline
FROM openjdk:13-jdk-alpine as build_ticketsonline

#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring

# /var/tmp - is in the host's file system
VOLUME /var/tmp
WORKDIR /opt/ticketsonline

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# image 2.)
FROM openjdk:13-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/opt/ticketsonline/target/dependency
COPY --from=build_ticketsonline ${DEPENDENCY}/BOOT-INF/lib /ticketsonline/lib
COPY --from=build_ticketsonline ${DEPENDENCY}/META-INF /ticketsonline/META-INF
COPY --from=build_ticketsonline ${DEPENDENCY}/BOOT-INF/classes /ticketsonline
ENTRYPOINT ["java","-cp","ticketsonline:ticketsonline/lib/*","springboot.ticketsonline.TicketsOnlineApplication"]