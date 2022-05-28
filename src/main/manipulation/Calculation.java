package com.example.RestService.manipulation;

import com.example.RestService.cache.Cache;
import com.example.RestService.counter.Counter;
import com.example.RestService.logger.MyLogger;
import com.example.RestService.responses.Response;

import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import static com.example.RestService.controllers.MainRestController.context;

public class Calculation {
    private static double finalSpeed;
    private static final Cache cache = context.getBean("cache", Cache.class);
    public static Counter counter = context.getBean("counter", Counter.class);

    public static double getFinalSpeed() {
        return finalSpeed;
    }

    public static void calculateFinalSpeed(@NotNull InputParameters parameters) {
        counter.increase();

        if(cache.isAdded(parameters)) {
            finalSpeed = Cache.getSpeed(parameters);
            return;
        }

        double speed = 0;

        speed = (double) (parameters.getFirstMass() * parameters.getFirstSpeed() +
                    parameters.getSecondSpeed() * parameters.getSecondMass()) /
                    (parameters.getFirstMass() + parameters.getSecondMass());

        speed = round(speed,2);
        MyLogger.log(Level.INFO, "Calculation finished");
        cache.addToMap(parameters, speed);

       finalSpeed = speed;
    }

    public static double round(double value, int places) {
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);

        return (double) tmp / factor;
    }

    public static @NotNull Response getResponse() {
        return new Response(finalSpeed);
    }

    public static boolean isCorrectParameters(@NotNull InputParameters parameters) {
        return parameters.getFirstMass() != null && parameters.getFirstSpeed() != null &&
                parameters.getSecondSpeed() != null && parameters.getSecondMass() != null &&
                parameters.getFirstMass() > 0 && parameters.getFirstSpeed() > 0 &&
                parameters.getSecondSpeed() > 0 && parameters.getSecondMass() > 0;
    }
}
