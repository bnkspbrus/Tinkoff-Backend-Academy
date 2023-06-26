package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.dto.OrderDto;
import com.tinkoffacademy.landscape.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Log
@RequiredArgsConstructor
public class OrderConsumer {
    private final KafkaTemplate<String, OrderDto> kafkaTemplate;
    private final OrderService orderService;

    @SendTo("landscape-rancher")
    @KafkaListener(id = "rancherLandscape", topics = "rancher-landscape")
    public OrderDto consumeRancher(OrderDto orderDto) {
        log.info("Order consumed by landscape from rancher: " + orderDto);
        Objects.requireNonNull(orderDto.getGardenId());
        Objects.requireNonNull(orderDto.getSkills());
        Objects.requireNonNull(orderDto.getStatus());
        switch (orderDto.getStatus()) {
            case CREATED -> {
                orderDto.setUserId(null);
                OrderDto saved = orderService.save(orderDto);
                log.info("Order saved by landscape: " + saved);
                List<User> users = orderService.findUsers(saved);
                for (User user : users) {
                    log.info("User found by landscape: " + user);
                    saved.setUserId(user.getId());
                    kafkaTemplate.send("landscape-handyman", saved);
                }
                saved.setUserId(null);
                return saved;
            }
            case APPROVED -> {
                Objects.requireNonNull(orderDto.getUserId());
                Objects.requireNonNull(orderDto.getId());
                orderService.updateById(orderDto.getId(), orderDto);
                log.info("Order updated by landscape: " + orderDto);
                return orderDto;
            }
            default -> throw new RuntimeException("Order with status " + orderDto.getStatus() + " can't be consumed by landscape from rancher");
        }
    }

    @KafkaListener(id = "handymanLandscape", topics = "handyman-landscape")
    public void consumeHandyman(OrderDto orderDto) {
        log.info("Order consumed by landscape from handyman: " + orderDto);
        Objects.requireNonNull(orderDto.getGardenId());
        Objects.requireNonNull(orderDto.getSkills());
        Objects.requireNonNull(orderDto.getStatus());
        Objects.requireNonNull(orderDto.getUserId());
        Objects.requireNonNull(orderDto.getId());
        switch (orderDto.getStatus()) {
            case IN_PROGRESS -> {
                OrderDto saved = orderService.getById(orderDto.getId());
                if (saved.getUserId() == null) {
                    orderService.updateById(orderDto.getId(), orderDto);
                    log.info("Order updated by landscape: " + orderDto);
                    kafkaTemplate.send("landscape-rancher", orderDto);
                }
            }
            case DONE -> {
                orderService.updateById(orderDto.getId(), orderDto);
                log.info("Order updated by landscape: " + orderDto);
                kafkaTemplate.send("landscape-rancher", orderDto);
            }
            default -> throw new RuntimeException("Order with status " + orderDto.getStatus() + " can't be consumed by landscape from handyman");
        }
    }
}
