package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.dto.OrderDto;
import com.tinkoffacademy.landscape.entity.Field;
import com.tinkoffacademy.landscape.entity.Order;
import com.tinkoffacademy.landscape.entity.User;
import com.tinkoffacademy.landscape.repository.FieldRepository;
import com.tinkoffacademy.landscape.repository.OrderRepository;
import com.tinkoffacademy.landscape.repository.UserRepository;
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
    private final FieldRepository fieldRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

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

    public List<User> findUsers(OrderDto orderDto) {
        Field garden = fieldRepository.findById(orderDto.getGardenId())
                .orElseThrow(
                        () -> new ResponseStatusException(NOT_FOUND, "Garden with id " + orderDto.getGardenId() + " not found")
                );
        List<User> users = userRepository.findByOrdersIsEmptyOrderByDistance(
                garden.getLatitude(),
                garden.getLongitude()
        );
        return users.stream()
                .filter(user -> user.getSkills().containsAll(orderDto.getSkills()))
                .limit(3)
                .toList();
    }

    public OrderDto save(OrderDto orderDto) {
        orderDto.setId(null);
        Order order = modelMapper.map(orderDto, Order.class);
        Order saved = orderRepository.save(order);
        return modelMapper.map(saved, OrderDto.class);
    }

    public OrderDto updateById(Long id, OrderDto orderDto) {
        orderDto.setId(id);
        Order order = modelMapper.map(orderDto, Order.class);
        Order saved = orderRepository.save(order);
        return modelMapper.map(saved, OrderDto.class);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
