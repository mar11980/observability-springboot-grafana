FROM eclipse-temurin:21-jdk

ARG JAR_FILE=target/*.jar

COPY ./target/grafana-0.0.1-SNAPSHOT.jar springbootgrafana.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "springbootgrafana.jar"]