package com.kafka.producer.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author EAverkin
 */
@Component
@ConfigurationProperties(prefix = "kafka.notification")
public class NotificationProperties extends ProducerProperties {
}
