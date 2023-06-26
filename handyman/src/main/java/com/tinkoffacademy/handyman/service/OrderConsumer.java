package com.tinkoffacademy.handyman.service;

import com.tinkoffacademy.handyman.enums.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import com.tinkoffacademy.dto.OrderDto;

import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@Service
@Log
@RequiredArgsConstructor
public class OrderConsumer {
    private final Random random = new Random();
    private final OrderProducer orderProducer;

    @KafkaListener(id = "landscapeHandyman", topics = "landscape-handyman")
    public void consume(OrderDto orderDto) {
        if (Objects.requireNonNull(orderDto.getStatus()) == Status.CREATED) {
            Objects.requireNonNull(orderDto.getGardenId());
            Objects.requireNonNull(orderDto.getSkills());
            Objects.requireNonNull(orderDto.getUserId());
            Objects.requireNonNull(orderDto.getId());
            if (!random.nextBoolean()) {
                log.info("Order declined: " + orderDto);
                return;
            }
            orderDto.setStatus(Status.IN_PROGRESS);
            log.info("Order in progress: " + orderDto);
            // create timer task which will change order status to DONE and send it to landscape
            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            orderDto.setStatus(Status.DONE);
                            log.info("Order done: " + orderDto);
                            orderProducer.send(orderDto);
                        }
                    },
                    random.nextLong(1000, 5000)
            );
            orderProducer.send(orderDto);
        }
        throw new RuntimeException("Status is not CREATED");
    }
}
