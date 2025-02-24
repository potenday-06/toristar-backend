FROM openjdk:17

ARG JAR_FILE=build/libs/*-api.jar

COPY ${JAR_FILE} app.jar

ENV TZ=Asia/Seoul

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]