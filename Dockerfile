FROM openjdk:17-alpine
ARG JAR_FILE=target/HW2-Kafka-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} producer.jar
ENTRYPOINT ["java","-jar","producer.jar"]