package com.nespresso.exercise.electric_trip;

import lombok.Getter;

public class City {

    public static City NO_CITY = null;

    @Getter
    private String name;

    @Getter
    private int chargerPower;

    private City nextCity;

    @Getter
    private int kmsToNextCity;

    public City(String cityName, City next) {
        this.name = cityName;
        this.nextCity = next;
    }

    public City(String cityName) {
        this.name = cityName;
        this.nextCity = City.NO_CITY;
    }

    public City() {
    }

    public City withName(String data) {
        this.name = data;
        return this;
    }

    public City withChargerPower(int chargerPower) {
        this.chargerPower = chargerPower;
        return this;
    }

    public City isNextOf(City previousCity) {
        if (NO_CITY != previousCity) {
            previousCity.nextCity = this;
        }
        return this;
    }

    public City next() {
        return nextCity;
    }

    public City kmsToNext(int kms) {
        this.kmsToNextCity = kms;
        return this;
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", kmsToNextCity=" + kmsToNextCity +
                '}';
    }
}
