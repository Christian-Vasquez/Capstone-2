package com.company.adminservice.feign;


import com.company.adminservice.util.message.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "level-up-service", path = "/level-up")
public interface LevelUpServiceFeign {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    LevelUp createLevelUp(@RequestBody LevelUp levelUp);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    LevelUp getLevelUp(@PathVariable("id") int id);

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateLevelUp(@RequestBody LevelUp levelUp);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteLevelUp(@PathVariable("/{id}") int id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<LevelUp> getAllLevelUps();

    @GetMapping("customer/{customer-id}")
    @ResponseStatus(HttpStatus.OK)
    LevelUp getLevelUpByCustomer(@PathVariable("customer-id") int id);

    @PostMapping("/{customer-id}")
    void addPointsToCustomer(@RequestBody int points, @PathVariable("customer-id") int id);

}
