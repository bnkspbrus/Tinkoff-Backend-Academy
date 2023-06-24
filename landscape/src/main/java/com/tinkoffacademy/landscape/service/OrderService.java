package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.landscape.dto.OrderDto;
import com.tinkoffacademy.landscape.entity.Order;
import com.tinkoffacademy.landscape.entity.User;
import com.tinkoffacademy.landscape.enums.Status;
import com.tinkoffacademy.landscape.repository.OrderRepository;
import com.tinkoffacademy.landscape.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final WebClient handymanWebClient;
    private final WebClient rancherWebClient;

    public OrderService(
            OrderRepository orderRepository,
            ModelMapper modelMapper,
            UserRepository userRepository,
            @Value("${handyman.baseUrl}") String handymanBaseUrl,
            @Value("${rancher.baseUrl}") String rancherBaseUrl
    ) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.handymanWebClient = WebClient.create(handymanBaseUrl);
        this.rancherWebClient = WebClient.create(rancherBaseUrl);
    }

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

    @Transactional(propagation = Propagation.NEVER)
    public OrderDto create(OrderDto orderDto) {
        orderDto.setUserId(null);
        orderDto.setStatus(Status.CREATED);
        OrderDto saved = save(orderDto);
        return findAndUpdateUsers(saved);
    }

    @Transactional
    public OrderDto findAndUpdateUsers(OrderDto currentOrder) {
        for (Order order : orderRepository.findByUserIsNullOrderByCreation()) {
            List<User> users = userRepository.findByOrdersIsEmptyOrderByDistance(
                    order.getGarden().getLatitude(),
                    order.getGarden().getLongitude()
            );
            List<User> suitable = users.stream()
                    .filter(user -> user.getSkills().containsAll(order.getSkills()))
                    .limit(3)
                    .toList();

            for (User user : suitable) {
                Boolean isAccepted = handymanWebClient.get()
                        .uri("/users/{id}/orders/accept?orderId={orderId}", user.getId(), order.getId())
                        .retrieve()
                        .bodyToMono(Boolean.class)
                        .retry(1)
                        .block();

                if (isAccepted != null && isAccepted) {
                    order.setUser(user);
                    order.setStatus(Status.IN_PROGRESS);
                    orderRepository.save(order);
                    if (order.getId().equals(currentOrder.getId()))
                        currentOrder.setUserId(user.getId());
                }
            }
        }
        return currentOrder;
    }

    private OrderDto save(OrderDto orderDto) {
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

    public void completeOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Order with id " + id + " not found"));
        order.setStatus(Status.DONE);
        orderRepository.save(order);

        rancherWebClient.get()
                .uri("/gardeners/{id}/review?orderId={orderId}", order.getGarden().getGardener().getId(), order.getId())
                .retrieve()
                .bodyToMono(Void.class)
                .retry(1) // retry in case of failure
                .subscribe();
    }

    public void approveOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Order with id " + id + " not found"));
        order.setStatus(Status.APPROVED);
        orderRepository.save(order);
    }
}
