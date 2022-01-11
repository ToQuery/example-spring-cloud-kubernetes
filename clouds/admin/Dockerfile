FROM toquery/base-java-runner:0.0.1

VOLUME /tmp
EXPOSE 8090
ADD ./target/example-spring-cloud-kubernetes-cloud-admin-0.0.1.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
