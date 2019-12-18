package com.company.retailapiservice.util.service;

import com.company.retailapiservice.util.feign.LevelUpClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelUpService {

    public static final String TOPIC_EXCHANGE_NAME = "level-up-exchange";
    public static final String ROUTING_KEY = "levelUp.create.levelUp";

    private RabbitTemplate rabbitTemplate;

    private LevelUpClient levelUpClient;

    @Autowired
    LevelUpService(RabbitTemplate rabbitTemplate, LevelUpClient levelUpClient) {
        this.rabbitTemplate = rabbitTemplate;
        this.levelUpClient = levelUpClient;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public int getTotalLevelUpPointsByCustomerId(int customerId) {
        return levelUpClient.getLevelUpPointsByCustomerId(customerId).getPoints();
    }

    public int reliable() {
        return 0000;
    }
}
