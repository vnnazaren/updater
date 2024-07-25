FROM openjdk:17-alpine
#ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:9091
ENV TZ="Europe/Moscow"
COPY target/*.jar stat-service.jar
ENTRYPOINT ["java","-jar","/stat-service.jar"]