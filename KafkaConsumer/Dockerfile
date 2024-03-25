FROM openjdk:17-alpine
ARG JAR_FILE=target/KafkaConsumer-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} consumer.jar
ENTRYPOINT ["java","-jar","consumer.jar"]