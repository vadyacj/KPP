package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class GreetingController {
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public GretingJava greeting(@RequestParam(value = "mas1", defaultValue = "1.0") double mas1, @RequestParam(value = "mas2", defaultValue = "2.0") double mas2,
                                @RequestParam(value = "speed1", defaultValue = "20.0") double speed1, @RequestParam(value = "speed2", defaultValue = "30.0") double speed2) {
        return new GretingJava(counter.incrementAndGet(), mas1, mas2, speed1, speed2, (mas1 * speed1 + mas2 * speed2)/(mas1+mas2) );
    }
}
