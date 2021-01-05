FROM maven:3.6-jdk-8-alpine AS build-env
WORKDIR /app
EXPOSE 8001
COPY pom.xml ./
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B package
FROM openjdk:8-jre-alpine
WORKDIR /app
RUN apk add --no-cache tzdata
ENV TZ=America/Lima
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY --from=build-env  app/target/*.jar /person.jar
CMD ["java", "-jar", "/person.jar"]