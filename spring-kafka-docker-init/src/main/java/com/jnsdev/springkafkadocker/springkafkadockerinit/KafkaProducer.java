package com.jnsdev.springkafkadocker.springkafkadockerinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @Autor Jairo Nascimento
 * @Created 25/11/2022 - 16:55
 */
@Service
public class KafkaProducer {

    private static final String TOPIC = "my_topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void writeMessage(String msg) {
        this.kafkaTemplate.send(TOPIC, msg);
    }
}
