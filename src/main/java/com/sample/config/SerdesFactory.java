package com.sample.config;

import com.sample.avro.Address;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


import java.util.Collections;
import java.util.Map;

@Configuration
public class SerdesFactory {

    private static Map<String, String> serdeConfig;

    @Value("${spring.kafka.properties.schema.registry.url}")
    public void setSchemaRegistry(String schemaRegistry){
        SerdesFactory.serdeConfig = Collections.singletonMap("schema.registry.url",
                schemaRegistry);
    }

    public static Serde<Address> addressSerde() {
        final Serde<Address> dataItemSerde = new SpecificAvroSerde<>();
        dataItemSerde.configure(serdeConfig, false);
        return dataItemSerde;
    }


}
