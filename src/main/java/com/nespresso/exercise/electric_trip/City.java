package com.nespresso.exercise.electric_trip;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class City {

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

        while (city.hasNext()) {
            kmsAccumulator += city.kmsToNextCity;
            city = city.nextCity;
        }

        return kmsAccumulator;
    }

    public boolean hasNext() {
        return null != nextCity;
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

    public City withPreviousCity(City previousCity) {
        if (null != previousCity) {
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
