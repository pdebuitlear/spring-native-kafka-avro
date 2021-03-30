package com.sample.topology;

import com.sample.avro.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@Slf4j
public class Producer {
    @Value("${topic.name}")
    private String TOPIC;

    private final KafkaTemplate<String, Address> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, Address> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Address address) {
        this.kafkaTemplate.send(this.TOPIC, address.getAddressId(), address);
        log.info(String.format("Produced address -> %s", address));
    }

}
