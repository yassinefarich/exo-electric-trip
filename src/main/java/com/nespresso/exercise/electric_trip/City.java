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


    public int calculateKmsToNextCities() {
        City city = this;
        int kmsAccumulator = 0;

        while (city.nextCity != NO_CITY) {
            kmsAccumulator += city.kmsToNextCity;
            city = city.nextCity;
        }

        return kmsAccumulator;
    }


    public City() {
    }

    public City(String cityName, City next) {
        this.name = cityName;
        this.nextCity = next;
    }

    public City(String cityName) {
        this(cityName, City.NO_CITY);
    }


    public City withName(String data) {
        this.name = data;
        return this;
    }

    public City withChargerPower(int chargerPower) {
        this.chargerPower = chargerPower;
        return this;
    }

    public City withPreviousCity(City previousCity) {
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

    public boolean isNamed(String name) {
        return this.name.equals(name);
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", chargerPower=" + chargerPower +
                ", kmsToNextCity=" + kmsToNextCity +
                '}';
    }

    public boolean hasCharger() {
        return chargerPower > 0;
    }
}
