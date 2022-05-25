package com.kafka.producer.config;

import com.kafka.producer.properties.NotificationProperties;
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
public class NotificationProducerConfig extends AbstractProducerConfig<String> {

    private final NotificationProperties properties;

    @Bean
    public DefaultKafkaProducerFactory<String, String> notificationProducerFactory() {
        return createProducerFactory(properties);
    }

    @Bean(name = "notificationKafkaTemplate")
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(notificationProducerFactory());
    }
}
