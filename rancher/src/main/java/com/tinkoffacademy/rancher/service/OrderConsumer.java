package com.tinkoffacademy.rancher.service;

import com.tinkoffacademy.dto.OrderDto;
import com.tinkoffacademy.dto.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

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

    @KafkaListener(id = "landscapeRancher", topics = "landscape-rancher")
    public void consume(OrderDto orderDto) {
        log.info("Order consumed by rancher: " + orderDto);
        if (Objects.requireNonNull(orderDto.getStatus()) == Status.DONE) {
            Objects.requireNonNull(orderDto.getGardenId());
            Objects.requireNonNull(orderDto.getSkills());
            Objects.requireNonNull(orderDto.getUserId());
            Objects.requireNonNull(orderDto.getId());
            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            orderDto.setStatus(Status.APPROVED);
                            log.info("Order approved: " + orderDto);
                            orderProducer.send(orderDto);
                        }
                    },
                    random.nextLong(1000, 5000)
            );
        } else {
            throw new RuntimeException("Status is not DONE");
        }
    }
}
