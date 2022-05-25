package com.kafka.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author EAverkin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String name;

    private int age;
}
