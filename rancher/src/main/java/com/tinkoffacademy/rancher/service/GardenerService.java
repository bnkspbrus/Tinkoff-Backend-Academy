package com.tinkoffacademy.rancher.service;

import java.util.List;

import com.tinkoffacademy.rancher.entity.Gardener;
import com.tinkoffacademy.rancher.repository.GardenerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class GardenerService {
    private final GardenerRepository gardenerRepository;

    public Gardener findById(Long id) {
        return gardenerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Gardener with id " + id + " not " +
                        "found"));
    }

    public List<Gardener> findAll() {
        return gardenerRepository.findAll();
    }

    public Gardener save(Gardener gardener) {
        return gardenerRepository.save(gardener);
    }

    public Gardener updateById(Long id, Gardener gardener) {
        gardener.setId(id);
        return gardenerRepository.save(gardener);
    }

    public void deleteById(Long id) {
        gardenerRepository.deleteById(id);
    }
}
