package com.jnsdev.springkafkadocker.springkafkadockerinit;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @Autor Jairo Nascimento
 * @Created 25/11/2022 - 17:07
 */
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "my_topic", groupId = "my_group_id")
    public void getMenssagem(String message) {
        System.out.println("MENSAGEM CONSUMER: " + message);
    }
}
