package com.kafka.producer;

import com.kafka.producer.dto.Order;
import com.kafka.producer.properties.OrderProducerProperties;
import com.kafka.producer.service.OrderService;
import kafka.server.KafkaServer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author EAverkin
 */
@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class KafkaIntegrationTest {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private OrderProducerProperties properties;

    @Autowired
    private OrderService orderService;

    private Consumer<String, Order> consumer;

    @BeforeEach
    void setUp() {
        Map<String, Object> configs = KafkaTestUtils.consumerProps("order", "false", embeddedKafkaBroker);
        DefaultKafkaConsumerFactory<String, Order> consumerFactory = new DefaultKafkaConsumerFactory<>(
                configs, new StringDeserializer(), new JsonDeserializer<>(Order.class));
        consumer = consumerFactory.createConsumer();
        consumer.subscribe(List.of(properties.getTopic()));
        consumer.poll(Duration.ZERO);
    }

    @AfterEach
    void tearDown() {
        if (consumer != null) {
            consumer.close();
        }
        embeddedKafkaBroker.getKafkaServers().forEach(KafkaServer::shutdown);
    }

    @Test
    void shouldSendOrder() {
        Order expectedOrder = Order.builder().name("order").age(10).build();
        orderService.sendOrder(expectedOrder);
        ConsumerRecord<String, Order> record = KafkaTestUtils.getSingleRecord(consumer, properties.getTopic());
        Order actualOrder = record.value();
        assertEquals(expectedOrder, actualOrder);
    }
}
