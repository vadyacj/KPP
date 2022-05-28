package com.example.RestService.controllers;

import com.example.RestService.SpringConfig;
import com.example.RestService.manipulation.Calculation;
import com.example.RestService.cache.Cache;
import com.example.RestService.manipulation.InputParameters;
import com.example.RestService.responses.Response;
import com.example.RestService.statistics.Statistics;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.RestService.manipulation.Calculation.counter;

@RestController
public class MainRestController {

    public static AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(SpringConfig.class);

    public static List<Response> list = new ArrayList<>();

    @GetMapping("/count")
    public ResponseEntity<Object> countFinalSpeed(
            @RequestParam(value = "firstMass") @Nullable Integer firstMass,
            @RequestParam(value = "secondMass") @Nullable Integer secondMass,
            @RequestParam(value = "firstSpeed") @Nullable Integer firstSpeed,
            @RequestParam(value = "secondSpeed") @Nullable Integer secondSpeed
    ) {
        var params = new InputParameters(firstMass, secondSpeed, secondMass, firstSpeed);

        if(!Calculation.isCorrectParameters(params)) {
            throw new IllegalArgumentException("Some parameters wrong!");
        }

        Calculation.calculateFinalSpeed(params);

        return new ResponseEntity<>(Calculation.getResponse(), HttpStatus.OK);
    }

    @GetMapping("/cache")
    public ResponseEntity<String> printCache() {
        return new ResponseEntity<>(Cache.getMap(), HttpStatus.OK);
    }

    @GetMapping("/counter")
    public ResponseEntity<String> printCounter() {
        var response = "Requests count: " + counter.getCount();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/stats")
    public ResponseEntity<Object> printStats() {
        return new ResponseEntity<>(Statistics.getResponse(), HttpStatus.OK);
    }

    @PostMapping("/solve_json")
    public ResponseEntity<Object> solveSingleJson(
            @RequestBody @NotNull InputParameters params
    ) {
        if(!Calculation.isCorrectParameters(params)) {
            throw new IllegalArgumentException("Some parameters wrong!");
        }

        Calculation.calculateFinalSpeed(params);

        return new ResponseEntity<>(Calculation.getResponse(), HttpStatus.OK);
    }

    @PostMapping("/solve_bulk")
    public ResponseEntity<Object> solveBulkJson(
            @RequestBody @NotNull List<InputParameters> params
    ) {
        list = params
                .stream()
                .filter(Calculation::isCorrectParameters)
                .peek(Calculation::calculateFinalSpeed)
                .map(e -> Calculation.getResponse())
                .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}