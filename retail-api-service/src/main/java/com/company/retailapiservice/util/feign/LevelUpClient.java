package com.company.retailapiservice.util.feign;

import com.company.retailapiservice.model.LevelUp;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("level-up-service")
public interface LevelUpClient {

//    must use this post method using a queue
    @PostMapping("/level-up")
    public LevelUp postLevelUpPoints(@RequestBody LevelUp levelUp);


//    need a circuit breaker pattern here
//    @HystrixCommand(fallbackMethod = "reliable")
    @GetMapping("/level-up/customer/{customer-id}")
    public LevelUp getLevelUpPointsByCustomerId(@PathVariable("customer-id") int customerId);


}
