FROM openjdk:8-jdk-alpine
MAINTAINER ToQuery <toquery@qq.com>
VOLUME /tmp
EXPOSE 8090
ADD ./target/example-spring-cloud-kubernetes-cloud-admin-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
