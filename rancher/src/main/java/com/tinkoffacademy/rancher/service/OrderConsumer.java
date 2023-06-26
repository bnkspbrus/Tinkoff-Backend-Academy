package com.tinkoffacademy.rancher.service;

import com.tinkoffacademy.dto.OrderDto;
import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log
public class OrderConsumer {
    @KafkaListener(id = "landscapeRancher", topics = "landscape-rancher")
    public void consume(OrderDto orderDto) {
        log.info("Order consumed by rancher: " + orderDto);
    }
}
