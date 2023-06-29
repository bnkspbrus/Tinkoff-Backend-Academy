package ru.tinkoff.rancher.controller;

import ru.tinkoff.rancher.dto.GardenerDto;
import ru.tinkoff.rancher.service.GardenerService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gardeners")
@RequiredArgsConstructor
public class GardenerController {
    private final GardenerService gardenerService;

    @GetMapping("/{id}")
    @Timed(value = "getById.time", description = "Time taken to get gardener by id")
    public GardenerDto getGardenerById(@PathVariable Long id) {
        return gardenerService.getGardenerById(id);
    }

    @GetMapping
    @Timed(value = "findAll.time", description = "Time taken to find all gardeners")
    public List<GardenerDto> getAllGardeners() {
        return gardenerService.getAllGardeners();
    }

    @PostMapping
    @Timed(value = "save.time", description = "Time taken to save gardener")
    public GardenerDto saveGardener(@RequestBody GardenerDto gardenerDto) {
        return gardenerService.saveGardener(gardenerDto);
    }

    @PutMapping
    @Timed(value = "updateById.time", description = "Time taken to update gardener by id")
    public GardenerDto updateGardener(@RequestBody GardenerDto gardenerDto) {
        return gardenerService.updateGardener(gardenerDto);
    }

    @DeleteMapping("/{id}")
    @Timed(value = "deleteById.time", description = "Time taken to delete gardener by id")
    public void deleteGardener(@PathVariable Long id) {
        gardenerService.deleteGardener(id);
    }
}
