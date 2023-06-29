package ru.tinkoff.landscape.service;

import ru.tinkoff.landscape.dto.OrderDto;
import ru.tinkoff.landscape.entity.Order;
import ru.tinkoff.landscape.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

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

    public OrderDto save(OrderDto orderDto) {
        orderDto.setId(null);
        Order order = modelMapper.map(orderDto, Order.class);
        return modelMapper.map(orderRepository.save(order), OrderDto.class);
    }

    public OrderDto updateById(Long id, OrderDto orderDto) {
        orderDto.setId(id);
        Order order = modelMapper.map(orderDto, Order.class);
        return modelMapper.map(orderRepository.save(order), OrderDto.class);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
