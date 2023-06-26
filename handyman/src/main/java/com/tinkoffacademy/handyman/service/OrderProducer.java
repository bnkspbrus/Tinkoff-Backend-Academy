package com.tinkoffacademy.handyman.service;

import com.tinkoffacademy.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, OrderDto> kafkaTemplate;

    public void send(OrderDto orderDto) {
        kafkaTemplate.send("handyman-landscape", orderDto);
    }
}
