package com.nespresso.exercise.electric_trip;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder()
public class Participant {

    @Getter
    private String startLocation;

    @Setter
    @Getter
    private City currentLocation;

    //@Setter @Getter
    //private long currentCharge;

    @Setter
    @Getter
    private double currentChargeInKw;

    private int batterySize;

    @Getter
    private int lowSpeedPerformance;

    @Getter
    private int highSpeedPerformance;


    public double calculateMaxDistWithLowSpeed() {
        return batterySize * lowSpeedPerformance;
    }

    public double calculateMaxDistWithHighSpeed() {
        return batterySize * highSpeedPerformance;
    }

    public void charge(int hoursOfCharge) {
        int chargeData = hoursOfCharge * currentLocation.getChargerPower();
        this.currentChargeInKw = currentChargeInKw + chargeData > batterySize ? batterySize
                : currentChargeInKw + chargeData;
    }

    public long calculateBatteryCharge() {
        return Math.round((this.currentChargeInKw / this.batterySize) * 100);
    }
}
