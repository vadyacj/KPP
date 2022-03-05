package com.example.restservice;

public class GretingJava {
    private final long id;
    private final double mas1;
    private final double mas2;
    private final double speed1;
    private final double speed2;
    private final double speed3;


    public GretingJava(long id, double mas1, double mas2, double speed1, double speed2, double speed3) {
        this.id = id;
        this.mas1 = mas1;
        this.mas2 = mas2;
        this.speed1 = speed1;
        this.speed2 = speed2;
        this.speed3 = speed3;
    }

    public long getId() {
        return id;
    }

    public double getMas1(){
        return mas1;
    }
    public double getMas2(){
        return mas2;
    }
    public double getSpeed1(){
        return speed1;
    }
    public double getSpeed2(){
        return speed2;
    }
    public double getSpeed3(){
        return speed3;
    }

}
