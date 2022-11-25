package com.jnsdev.springkafkadocker.springkafkadockerinit;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Autor Jairo Nascimento
 * @Created 25/11/2022 - 17:03
 */
@RestController
public class KafkaController {

    private final KafkaProducer producer;

    public KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping("publish")
    public void writrMessageToTopic(@RequestParam("message") String message) {
        this.producer.writeMessage(message);
    }
}
