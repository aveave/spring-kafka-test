package com.kafka.producer.config;

import com.kafka.producer.dto.Order;
import com.kafka.producer.properties.OrderProducerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author EAverkin
 */
@Configuration
@RequiredArgsConstructor
public class OrderProducerConfig extends AbstractProducerConfig<Order> {

    private final OrderProducerProperties properties;

    @Bean
    public DefaultKafkaProducerFactory<String, Order> orderProducerFactory() {
        return createProducerFactory(properties);
    }

    @Bean(name = "orderKafkaTemplate")
    public KafkaTemplate<String, Order> kafkaTemplate() {
        return new KafkaTemplate<>(orderProducerFactory());
    }
}
