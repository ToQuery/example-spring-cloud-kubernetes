FROM toquery/s2i-java8-runner:0.0.1

EXPOSE 8080
VOLUME /tmp
ADD ./target/example-spring-cloud-kubernetes-hello-world-0.0.1.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
HEALTHCHECK --start-period=50s --interval=1m --timeout=10s --retries=5 \
            CMD curl --silent --fail --request GET http://localhost:8080/actuator/health \
                | jq --exit-status '.status == "UP"' || exit 1
