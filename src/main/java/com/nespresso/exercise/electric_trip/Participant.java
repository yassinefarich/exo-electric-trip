package com.nespresso.exercise.electric_trip;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder()
public class Participant {

    @Getter
    private String startLocation;

    @Setter @Getter
    private City currentLocation;

    @Setter @Getter
    private long currentCharge;

    private int batterySize;

    private int lowSpeedPerformance;
    private int highSpeedPerformance;


    public double calculateMaxDistWithLowSpeed() {
        return batterySize * lowSpeedPerformance;
    }

    public double calculateMaxDistWithHighSpeed() {
        return batterySize * highSpeedPerformance;
    }

    public void charge(int hoursOfCharge) {
        int chargeData = hoursOfCharge * currentLocation.getChargerPower();
    }
}
