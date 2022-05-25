package com.kafka.producer.controller;

import com.kafka.producer.dto.Order;
import com.kafka.producer.properties.OrderProducerProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author EAverkin
 */
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducerProperties properties;
    private final KafkaTemplate<String, Order> kafkaTemplate;

    @PostMapping("/order")
    public void createOrder(@RequestBody Order order) {
        ProducerRecord<String, Order> record = new ProducerRecord<>(properties.getTopic(), order);
        kafkaTemplate.send(record);
    }
}
