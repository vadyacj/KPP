package com.example.restservice;

import com.example.restservice.Speed;

public class Object {
    private double mass;
    private double speed;

    public Object (double mass_m, double speed_m){
        this.mass = mass_m;
        this.speed = speed_m;
    }

    public Speed result(Object obj2){
        return new Speed((this.mass * this.speed + obj2.getMass() * obj2.getSpeed())/(this.mass + obj2.getMass()));
    }

    public double getMass() {
        return mass;
    }
    public double getSpeed() {
        return speed;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
