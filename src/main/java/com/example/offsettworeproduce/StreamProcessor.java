package com.example.offsettworeproduce;

import com.example.offsettworeproduce.avro.Input;
import com.example.offsettworeproduce.avro.Output;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StreamProcessor {
    private static final String INPUT_TOPIC = "input-topic";
    private static final String OUTPUT_TOPIC = "output-topic";
    private final Serde<Input> inputSerde;
    private final Serde<Output> outputSerde;

    public StreamProcessor(Serde<Input> inputSerde, Serde<Output> outputSerde) {
        this.inputSerde = inputSerde;
        this.outputSerde = outputSerde;
    }

    @Autowired
    public void buildPipeline(StreamsBuilder streamsBuilder) {
        streamsBuilder.stream(INPUT_TOPIC, Consumed.with(Serdes.String(), inputSerde))
                .mapValues(input -> new Output(input.getName(), input.getAge(), true))
                .to(OUTPUT_TOPIC, Produced.with(Serdes.String(), outputSerde));
    }
}
