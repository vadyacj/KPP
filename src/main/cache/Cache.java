package com.example.RestService.cache;

import com.example.RestService.logger.MyLogger;
import com.example.RestService.manipulation.InputParameters;
import org.jetbrains.annotations.NotNull;
import org.apache.logging.log4j.Level;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.HashMap;

public class Cache {
    private static final HashMap<InputParameters, Double> calculations = new HashMap<>();
    private static final ArrayList<Double> speedList = new ArrayList<>();

    public void addToMap(@NotNull InputParameters parameters, Double finalSpeed) {
        calculations.put(parameters, finalSpeed);
        speedList.add(finalSpeed);
        MyLogger.log(Level.INFO, "Record added to map");
    }

    public boolean isAdded(@NotNull InputParameters parameters) {
        if(calculations.containsKey(parameters)) {
            MyLogger.log(Level.INFO, "Record found in map");
            return true;
        } else {
            MyLogger.log(Level.INFO, "Record not found in map");
            return false;
        }
    }

    public static double getSpeed(@NotNull InputParameters parameters) {
        return calculations.get(parameters);
    }

    public static String getMap() {
        return ("Cache:\n" + speedList);
    }

    @PreDestroy
    void preDestroy() {
        calculations.clear();
        speedList.clear();
        MyLogger.log(Level.INFO, "Map was cleared");
    }
}
