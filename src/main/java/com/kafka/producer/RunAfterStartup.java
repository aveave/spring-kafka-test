package com.kafka.producer;

import com.kafka.producer.properties.NotificationProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author EAverkin
 */
@Component
@RequiredArgsConstructor
public class RunAfterStartup {

    @Qualifier("notificationKafkaTemplate")
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final NotificationProperties properties;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        ProducerRecord<String, String> record = new ProducerRecord<>(properties.getTopic(), "Application successfully started");
        executorService.scheduleAtFixedRate(() -> kafkaTemplate.send(record), 1, 5, TimeUnit.SECONDS);
    }
}
