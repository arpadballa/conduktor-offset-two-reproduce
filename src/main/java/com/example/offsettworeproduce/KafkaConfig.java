package com.example.offsettworeproduce;

import com.example.offsettworeproduce.avro.Input;
import com.example.offsettworeproduce.avro.Output;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.serialization.Serde;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class KafkaConfig {
    @Bean
    public Map<String, String> schemaRegistryConfig() {
        return Map.of("schema.registry.url", "http://localhost:8081");
    }

    @Bean
    public Serde<Input> inputSerde() {
        return createSerde();
    }

    @Bean
    public Serde<Output> outputSerde() {
        return createSerde();
    }

    private <T extends SpecificRecord> SpecificAvroSerde<T> createSerde() {
        SpecificAvroSerde<T> serde = new SpecificAvroSerde<>();
        serde.configure(schemaRegistryConfig(), false);
        return serde;
    }
}
