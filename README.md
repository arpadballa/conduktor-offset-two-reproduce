# Process to reproduce streams offset issue

## Steps

1. Start the dependencies in docker-compose
2. Build and run the project (`./mvnw spring-boot:run`)
3. Go to [Conduktor](http://localhost:8080) and produce to `input-topic`

## Observation
Note the following property:
```
spring.kafka.streams.properties.processing.guarantee=exactly_once_v2
```
in `application.properties`. This is to enable exactly-once processing semantics in Kafka Streams.
This also causes the offset to move by two every time the streams application consumes a message from `input-topic`.

What you see in Conduktor: `x matching records`, and `x / x*2 consumed`. It also keeps consuming, so it fails to detect that it reached the end of the records.
