package com.nespresso.exercise.electric_trip;

public class City {

    public static City NO_CITY = new City("NULL_CITY", null);

    private String name;
    private City nextCity;
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

    public City nextOf(City previousCity) {
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

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", kmsToNextCity=" + kmsToNextCity +
                '}';
    }
}
