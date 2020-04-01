FROM openjdk:8-jdk-alpine
MAINTAINER ToQuery <toquery@qq.com>
VOLUME /tmp
ADD ./target/example-spring-cloud-kubernetes-hello-world-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
