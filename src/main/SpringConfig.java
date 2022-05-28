package com.example.RestService;

import com.example.RestService.cache.Cache;
import com.example.RestService.counter.Counter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpringConfig {

    @Bean("cache")
    @Scope(value = "singleton")
    Cache cache() {
        return new Cache();
    }

    @Bean("counter")
    @Scope(value = "singleton")
    Counter counter() {
        return new Counter();
    }
}