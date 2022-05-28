package com.example.RestService.counter;

import com.example.RestService.logger.MyLogger;
import lombok.Synchronized;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Contract;

public class Counter {

    private long count;

    @Contract(pure = true)
    public long getCount() {
        return count;
    }

    @Synchronized
    public synchronized void increase() {
        MyLogger.log(Level.INFO, "Requests amount: " + ++count);
    }
}
