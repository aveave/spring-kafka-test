package com.kafka.producer.service.impl;

import com.kafka.producer.dto.Order;
import com.kafka.producer.properties.OrderProducerProperties;
import com.kafka.producer.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author EAverkin
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderProducerProperties properties;
    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Override
    public void sendOrder(Order order) {
        ProducerRecord<String, Order> record = new ProducerRecord<>(properties.getTopic(), order);
        kafkaTemplate.send(record);
    }
}
