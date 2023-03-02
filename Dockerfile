FROM openjdk:17-alpine
EXPOSE 9091
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} videoblog-app-core.jar
ENTRYPOINT ["java","-jar","/videoblog-app-core.jar"]