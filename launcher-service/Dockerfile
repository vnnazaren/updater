FROM openjdk:17-alpine
#FROM amazoncorretto:22.0.1-alpine3.19
#ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:9091
ENV TZ="Europe/Moscow"
COPY target/*.jar launcher-service.jar
ENTRYPOINT ["java","-jar","/launcher-service.jar"]