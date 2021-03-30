package com.sample.controllers;

import com.sample.avro.Address;
import com.sample.model.AddrReq;
import com.sample.topology.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/address")
public class KafkaController {

    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping
    public void sendMessageToKafkaTopic(@RequestBody AddrReq addr) {
        Address address = Address.newBuilder().setAddressId(UUID.randomUUID().toString()).setAddressPostcode(addr.getPostcode()).build();
        this.producer.sendMessage(address);
    }

}
