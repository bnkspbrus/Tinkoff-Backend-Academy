package com.tinkoffacademy.landscape.service;

import java.util.List;

import com.tinkoffacademy.landscape.dto.OrderDto;
import com.tinkoffacademy.landscape.entity.Order;
import com.tinkoffacademy.landscape.entity.User;
import com.tinkoffacademy.landscape.repository.AccountRepository;
import com.tinkoffacademy.landscape.repository.FieldRepository;
import com.tinkoffacademy.landscape.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;
    private final FieldRepository fieldRepository;

    public OrderDto getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Order with id " + id + " not found"));
        return modelMapper.map(order, OrderDto.class);
    }

    public List<OrderDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    public List<OrderDto> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .getContent()
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    public OrderDto save(OrderDto order) {
        order.setId(null);
        return modelMapper.map(orderRepository.save(convertToEntity(order)), OrderDto.class);
    }

    public OrderDto updateById(Long id, OrderDto order) {
        order.setId(id);
        return modelMapper.map(orderRepository.save(convertToEntity(order)), OrderDto.class);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    private Order convertToEntity(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);

        order.setUser((User) accountRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "User with id " + orderDto.getUserId() + " not found"
                )));

        order.setGarden(fieldRepository.findById(orderDto.getGardenId())
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Garden with id " + orderDto.getGardenId() + " not found"
                )));
        return order;
    }
}
