package com.tinkoffacademy.rancher.controller;

import java.util.List;

import com.tinkoffacademy.rancher.entity.Gardener;
import com.tinkoffacademy.rancher.service.GardenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gardeners")
public record GardenerController(
        GardenerService gardenerService
) {
    @GetMapping("/{id}")
    public Gardener findById(@PathVariable Long id) {
        return gardenerService.findById(id);
    }

    @GetMapping
    public List<Gardener> findAll() {
        return gardenerService.findAll();
    }

    @PostMapping
    public Gardener save(@RequestBody Gardener gardener) {
        return gardenerService.save(gardener);
    }

    @PutMapping("/{id}")
    public Gardener updateById(@PathVariable Long id, @RequestBody Gardener gardener) {
        return gardenerService.updateById(id, gardener);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        gardenerService.deleteById(id);
    }
}
