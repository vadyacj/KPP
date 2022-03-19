package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/obj")
    public Speed objReturn(
            @RequestParam(value = "mass1", defaultValue = "2") double mass1,
            @RequestParam(value = "speed1", defaultValue = "3") double speed1,
            @RequestParam(value = "speed2", defaultValue = "7") double speed2,
            @RequestParam(value = "mass2", defaultValue = "8") double mass2
        ) {
        Object obj1 = new Object(mass1, speed1);
        Object obj2 = new Object(mass2, speed2);

        return obj1.result(obj2);
    }
}
