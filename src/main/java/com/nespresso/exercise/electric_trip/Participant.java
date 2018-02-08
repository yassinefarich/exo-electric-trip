package com.nespresso.exercise.electric_trip;

import lombok.Getter;
import lombok.Setter;

public class Participant {


    @Setter
    @Getter
    private City location;

    @Setter
    @Getter
    private double currentChargeInKw;

    private int batterySize;

    @Getter
    private int lowSpeedPerformance;

    @Getter
    private int highSpeedPerformance;

    Participant(City location, double currentChargeInKw, int batterySize, int lowSpeedPerformance, int highSpeedPerformance) {
        this.location = location;
        this.currentChargeInKw = currentChargeInKw;
        this.batterySize = batterySize;
        this.lowSpeedPerformance = lowSpeedPerformance;
        this.highSpeedPerformance = highSpeedPerformance;
    }

    Participant(City location, int batterySize, int lowSpeedPerformance, int highSpeedPerformance) {
        this(location, batterySize, batterySize, lowSpeedPerformance, highSpeedPerformance);

    }

    public static ParticipantBuilder builder() {
        return new ParticipantBuilder();
    }


    public double calculateMaxDistWithLowSpeed() {
        return currentChargeInKw * lowSpeedPerformance;
    }

    public double calculateMaxDistWithHighSpeed() {
        return currentChargeInKw * highSpeedPerformance;
    }

    public void charge(int hoursOfCharge) {
        int chargeData = hoursOfCharge * location.getChargerPower();
        this.currentChargeInKw = currentChargeInKw + chargeData > batterySize ? batterySize
                : currentChargeInKw + chargeData;
    }

    public long calculateBatteryCharge() {
        return Math.round((this.currentChargeInKw / this.batterySize) * 100);
    }


    public static class ParticipantBuilder {
        private City location;
        private double currentChargeInKw;
        private int batterySize;
        private int lowSpeedPerformance;
        private int highSpeedPerformance;

        ParticipantBuilder() {
        }

        public ParticipantBuilder location(City location) {
            this.location = location;
            return this;
        }

        public ParticipantBuilder currentChargeInKw(double currentChargeInKw) {
            this.currentChargeInKw = currentChargeInKw;
            return this;
        }

        public ParticipantBuilder batterySize(int batterySize) {
            this.batterySize = batterySize;
            return this;
        }

        public ParticipantBuilder lowSpeedPerformance(int lowSpeedPerformance) {
            this.lowSpeedPerformance = lowSpeedPerformance;
            return this;
        }

        public ParticipantBuilder highSpeedPerformance(int highSpeedPerformance) {
            this.highSpeedPerformance = highSpeedPerformance;
            return this;
        }

        public Participant build() {
            return new Participant(location, batterySize, lowSpeedPerformance, highSpeedPerformance);
        }

        public String toString() {
            return "Participant.ParticipantBuilder(location=" + this.location + ", currentChargeInKw=" + this.currentChargeInKw + ", batterySize=" + this.batterySize + ", lowSpeedPerformance=" + this.lowSpeedPerformance + ", highSpeedPerformance=" + this.highSpeedPerformance + ")";
        }
    }
}
