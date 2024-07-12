package com.example.offsettworeproduce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class OffsetTwoReproduceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OffsetTwoReproduceApplication.class, args);
	}

}
