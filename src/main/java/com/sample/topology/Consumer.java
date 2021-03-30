package com.sample.topology;


import com.sample.avro.Address;
import com.sample.config.SerdesFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Configuration
@EnableKafka
@EnableKafkaStreams
@Slf4j
public class Consumer {

    @Value("${topic.name}")
    private String TOPIC;

    @Bean("addressKTable")
    public KStream<String, Address> facilityAggregationKTable(StreamsBuilder builder) {
         return builder
                .stream(
                        TOPIC,
                        Consumed.with(Serdes.String(), SerdesFactory.addressSerde()))
                .peek((key, address) -> log.info("Address: {}",address));
    }
}