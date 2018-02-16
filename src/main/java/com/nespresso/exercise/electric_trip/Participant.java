package com.nespresso.exercise.electric_trip;

import lombok.Builder;
import lombok.Getter;

@Builder
public class Participant {

    @Getter
    private City location;

    private double currentChargeInKw;
    private int batterySize;
    private int lowSpeedPerformance;
    private int highSpeedPerformance;

    Participant(City location, double currentChargeInKw, int batterySize, int lowSpeedPerformance, int highSpeedPerformance) {
        this.location = location;
        this.currentChargeInKw = currentChargeInKw;
        this.batterySize = batterySize;
        this.lowSpeedPerformance = lowSpeedPerformance;
        this.highSpeedPerformance = highSpeedPerformance;
    }

    public void charge(int hoursOfCharge) {
        int chargeData = hoursOfCharge * location.getChargerPower();
        this.currentChargeInKw = currentChargeInKw + chargeData > batterySize ? batterySize
                : currentChargeInKw + chargeData;
    }

    public long calculateBatteryCharge() {
        return Math.round((this.currentChargeInKw / this.batterySize) * 100);
    }

    public void go() {
        City city = location;
        while (city.hasNext() && doIHaveToGoTo(city)) {
            updateCurrentChargeWithLowSpeed(city.getKmsToNextCity());
            city = city.next();
            location = city;
        }
    }

    //TODO : Find a good name for those functions
    private void updateCurrentChargeWithLowSpeed(int kmsToNextCity) {
        double maxDistance = calculateMaxDistWithLowSpeed() - kmsToNextCity;
        currentChargeInKw = maxDistance / lowSpeedPerformance;
    }

    private double calculateMaxDistWithLowSpeed() {
        return currentChargeInKw * lowSpeedPerformance;
    }

    public void sprint() {
        City city = location;
        while (city.hasNext() && doIHaveToGoTo(city)) {
            updateCurrentChargeWithHighSpeed(city.getKmsToNextCity());
            city = city.next();
            location = city;
        }
    }

    private void updateCurrentChargeWithHighSpeed(int kmsToNextCity) {
        double maxDistance = calculateMaxDistWithHighSpeed() - kmsToNextCity;
        currentChargeInKw = maxDistance / highSpeedPerformance;
    }

    private double calculateMaxDistWithHighSpeed() {
        return currentChargeInKw * highSpeedPerformance;
    }

    private boolean doIHaveToGoTo(City city) {
        if (city.hasCharger()) {
            return canGoUpTo(city.calculateKmsToNextCities()) ? true
                    : isFullyCharged();
        }
        return canGoUpTo(city.getKmsToNextCity());
    }

    private boolean isFullyCharged() {
        return currentChargeInKw == batterySize;
    }

    private boolean canGoUpTo(int kmsToNextCity) {
        return calculateMaxDistWithLowSpeed() > kmsToNextCity;
    }
}
