package com.example.RestService.manipulation;

import com.example.RestService.logger.MyLogger;

import org.apache.logging.log4j.Level;
import org.springframework.lang.Nullable;

import java.util.Objects;

public class InputParameters {
    private final @Nullable Integer firstMass;
    private final @Nullable Integer secondSpeed;
    private final @Nullable Integer secondMass;
    private final @Nullable Integer firstSpeed;

    public InputParameters(
            @Nullable Integer firstMass,
            @Nullable Integer secondSpeed,
            @Nullable Integer secondMass,
            @Nullable Integer firstSpeed
    ) {
        MyLogger.log(Level.INFO, "InputParameters constructor");

        this.firstMass = firstMass;
        this.secondSpeed = secondSpeed;
        this.secondMass = secondMass;
        this.firstSpeed = firstSpeed;
    }

    @Nullable
    public Integer getFirstMass() {
        return firstMass;
    }

    @Nullable
    public Integer getSecondSpeed() {
        return secondSpeed;
    }

    @Nullable
    public Integer getSecondMass() {
        return secondMass;
    }

    @Nullable
    public Integer getFirstSpeed() {
        return firstSpeed;
    }

    // переобределение метода equals()
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        InputParameters parameters = (InputParameters) obj;

        return  Objects.equals(firstMass, parameters.getFirstMass())  &&
                Objects.equals(secondMass, parameters.getSecondMass()) &&
                Objects.equals(firstSpeed, parameters.getFirstSpeed())  &&
                Objects.equals(secondSpeed, parameters.getSecondSpeed());
    }

    // переопределение метода hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(firstMass, secondMass, firstSpeed, secondSpeed);
    }
}
