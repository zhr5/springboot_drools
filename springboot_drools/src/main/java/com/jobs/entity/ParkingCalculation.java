package com.jobs.entity;

import lombok.Data;

@Data
public class ParkingCalculation {
    private String carType;
    private Double duration;
    private int numOfDays;
    private double parkingFee;
}
