FROM openjdk:8-jdk-alpine
RUN apk --no-cache add curl
WORKDIR  /spring-boot-best-practices
ARG REGION_ARG=ap-south-1
ARG ACCESS_ARG
ARG SECRET_ARG
ENV AWS_REGION=$REGION_ARG
ENV AWS_ACCESS_KEY=$ACCESS_ARG
ENV AWS_SECRET_KEY=$SECRET_ARG
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]