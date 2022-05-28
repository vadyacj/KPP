package com.example.RestService.statistics;

import com.example.RestService.logger.MyLogger;
import com.example.RestService.responses.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.apache.logging.log4j.Level;

import java.util.Collections;
import java.util.Comparator;
import java.util.function.BinaryOperator;

import static com.example.RestService.controllers.MainRestController.list;

@Getter
public class Statistics {
    @JsonProperty("Amount of requests")
    int requestsAmount;
    @JsonProperty("Min result")
    double minResult;
    @JsonProperty("Max result")
    double maxResult;
    @JsonProperty("Most common response")
    double mostCommonRequest;

    public Statistics(int requestsAmount, double minResult,
                              double maxResult, double mostCommon
    ){
        MyLogger.log(Level.INFO, "stats constructor");
        this.requestsAmount = requestsAmount;
        this.minResult = minResult;
        this.maxResult = maxResult;
        this.mostCommonRequest = mostCommon;
    }

    public static Object getResponse() {
        MyLogger.log(Level.INFO, "Stats");

        Response mostCommonRequestTemp = list
                .stream()
                .reduce(
                        BinaryOperator.maxBy(Comparator.comparingDouble(o -> Collections.frequency(list, o)))
                ).orElse(new Response(0D));

        double mostCommonRequest = (double) mostCommonRequestTemp.getResult();

        double maxResult = list
                .stream()
                .mapToDouble((value) -> (double) value.getResult())
                .summaryStatistics()
                .getMax();

        double minResult = list
                .stream()
                .mapToDouble((value) -> (double) value.getResult())
                .summaryStatistics()
                .getMin();

        int requestsAmount = list.size();

        return new Statistics(requestsAmount, minResult, maxResult, mostCommonRequest);
    }
}

