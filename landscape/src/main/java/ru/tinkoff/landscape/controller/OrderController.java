package ru.tinkoff.landscape.controller;

import ru.tinkoff.landscape.dto.OrderDto;
import ru.tinkoff.landscape.service.OrderService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    @Timed(value = "getById.time", description = "Time taken to return an order")
    public OrderDto getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping
    @Timed(value = "findAll.time", description = "Time taken to return all orders")
    public List<OrderDto> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/page")
    @Timed(value = "findAllPage.time", description = "Time taken to return all orders with pagination")
    public List<OrderDto> findAll(@RequestParam int page, @RequestParam int size) {
        return orderService.findAll(PageRequest.of(page, size));
    }

    @PostMapping
    @Timed(value = "save.time", description = "Time taken to save an order")
    public OrderDto save(@RequestBody OrderDto order) {
        return orderService.save(order);
    }

    @PutMapping("/{id}")
    @Timed(value = "updateById.time", description = "Time taken to update an order")
    public OrderDto updateById(@PathVariable Long id, @RequestBody OrderDto order) {
        return orderService.updateById(id, order);
    }

    @DeleteMapping("/{id}")
    @Timed(value = "deleteById.time", description = "Time taken to delete an order")
    public void deleteById(@PathVariable Long id) {
        orderService.deleteById(id);
    }
}
