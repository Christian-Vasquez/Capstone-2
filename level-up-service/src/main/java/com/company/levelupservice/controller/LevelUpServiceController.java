package com.company.levelupservice.controller;

import com.company.levelupservice.model.LevelUp;
import com.company.levelupservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/level-up")
@CacheConfig(cacheNames = {"level-ups"})
public class LevelUpServiceController {

    @Autowired
    ServiceLayer service;

    @CachePut(key = "#result.getLevelUpId()")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUp createLevelUp(@RequestBody LevelUp levelUp) {
        return service.saveLevelUp(levelUp);
    }

    @Cacheable
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LevelUp getLevelUp(@PathVariable("id") int id) {
        return service.findLevelUpByLvlId(id);
    }

    @CacheEvict(key = "#level.getLevelUpId()")
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLevelUp(@RequestBody LevelUp levelUp) {
        service.updateLevelUp(levelUp);
    }

    @CacheEvict
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUp(@PathVariable("/{id}") int id) {
        service.deleteLevelUp(id);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUp> getAllLevelUps() {
        return service.getAllLevelUps();
    }

    @GetMapping("customer/{customer-id}")
    @ResponseStatus(HttpStatus.OK)
    public LevelUp getLevelUpByCustomer(@PathVariable("customer-id") int id) {
        return service.getLevelUpByCustomerId(id);
    }

    @PostMapping("/{customer-id}")
    public void addPointsToCustomer(@RequestBody int points, @PathVariable("customer-id") int id) {
        service.addPointsByCustomerId(points, id);
    }

}
