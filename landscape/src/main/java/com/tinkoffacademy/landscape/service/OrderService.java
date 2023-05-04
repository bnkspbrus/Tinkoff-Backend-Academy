package com.tinkoffacademy.landscape.service;

import java.util.List;

import com.tinkoffacademy.landscape.entity.Order;
import com.tinkoffacademy.landscape.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Order with id " + id + " not " +
                        "found"));
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable).getContent();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order updateById(Long id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
