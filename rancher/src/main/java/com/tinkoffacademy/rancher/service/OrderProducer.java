package com.tinkoffacademy.rancher.service;

import com.tinkoffacademy.dto.OrderDto;
import com.tinkoffacademy.dto.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Log
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, OrderDto> kafkaTemplate;

    public void send(OrderDto orderDto) {
        Objects.requireNonNull(orderDto.getGardenId());
        Objects.requireNonNull(orderDto.getSkills());
        orderDto.setStatus(Status.CREATED);
        kafkaTemplate.send("rancher-landscape", orderDto);
    }
}
