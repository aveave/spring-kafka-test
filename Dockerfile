FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine

WORKDIR /app

COPY target/kafka-producer-0.0.1-SNAPSHOT.jar kafka-producer.jar

ENV PORT 9000

EXPOSE ${PORT}

ENTRYPOINT exec java -jar kafka-producer.jar