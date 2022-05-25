package com.kafka.producer.service;

import com.kafka.producer.dto.Order;

/**
 * @author EAverkin
 */
public interface OrderService {

    void sendOrder(Order order);
}
