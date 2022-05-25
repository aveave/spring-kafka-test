package com.kafka.producer.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author EAverkin
 */
@Getter
@Setter
public abstract class ProducerProperties {

    private String bootstrapServer;

    private String topic;
}
