package com.tinkoffacademy.rancher.controller;

import java.util.List;

import com.tinkoffacademy.rancher.entity.Gardener;
import com.tinkoffacademy.rancher.service.GardenerService;
import io.micrometer.core.annotation.Timed;
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
@RequiredArgsConstructor
public class GardenerController {
    private final GardenerService gardenerService;

    @GetMapping("/{id}")
    @Timed(value = "getById.time", description = "Time taken to get gardener by id")
    public Gardener findById(@PathVariable Long id) {
        return gardenerService.findById(id);
    }

    @GetMapping
    @Timed(value = "findAll.time", description = "Time taken to find all gardeners")
    public List<Gardener> findAll() {
        return gardenerService.findAll();
    }

    @PostMapping
    @Timed(value = "save.time", description = "Time taken to save gardener")
    public Gardener save(@RequestBody Gardener gardener) {
        return gardenerService.save(gardener);
    }

    @PutMapping("/{id}")
    @Timed(value = "updateById.time", description = "Time taken to update gardener by id")
    public Gardener updateById(@PathVariable Long id, @RequestBody Gardener gardener) {
        return gardenerService.updateById(id, gardener);
    }

    @DeleteMapping("/{id}")
    @Timed(value = "deleteById.time", description = "Time taken to delete gardener by id")
    public void deleteById(@PathVariable Long id) {
        gardenerService.deleteById(id);
    }
}
