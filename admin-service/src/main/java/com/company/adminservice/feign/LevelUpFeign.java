package com.company.adminservice.feign;

import com.company.adminservice.util.message.LevelUpClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "level-up-service", path = "/level-up")
public interface LevelUpFeign {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    LevelUpClient createLevelUp(@RequestBody LevelUpClient levelUpClient);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    LevelUpClient getLevelUp(@PathVariable("id") int id);

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateLevelUp(@RequestBody LevelUpClient levelUpClient);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteLevelUp(@PathVariable("/{id}") int id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<LevelUpClient> getAllLevelUps();

    @GetMapping("customer/{customer-id}")
    @ResponseStatus(HttpStatus.OK)
    LevelUpClient getLevelUpByCustomer(@PathVariable("customer-id") int id);

    @PostMapping("/{customer-id}")
    void addPointsToCustomer(@RequestBody int points, @PathVariable("customer-id") int id);

}
