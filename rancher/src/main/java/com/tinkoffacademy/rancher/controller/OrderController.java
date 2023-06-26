package com.tinkoffacademy.rancher.controller;

import com.tinkoffacademy.dto.OrderDto;
import com.tinkoffacademy.rancher.service.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderProducer orderProducer;

    @PostMapping("/create")
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderProducer.send(orderDto);
    }
}
