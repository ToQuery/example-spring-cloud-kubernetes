FROM toquery/s2i-java8-runner:0.0.1

VOLUME /tmp
EXPOSE 8011
ADD ./target/example-spring-cloud-kubernetes-server-account-0.0.1.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
