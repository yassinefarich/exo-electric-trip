package com.nespresso.exercise.electric_trip;

import java.util.List;

public class TripMap {

    City firstCity = City.NO_CITY;

    public TripMap(String tripMap) {
        String[] mapData = tripMap.split("-");

        City city = City.NO_CITY;
        City previousCity = City.NO_CITY;

        for (String data : mapData) {

            if (isCity(data)) {
                city = new City()
                        .withName(data)
                        .nextOf(previousCity);
                System.out.println(firstCity);

                if (City.NO_CITY == firstCity) {
                    firstCity = city;
                }
                previousCity = city;

            } else {
                previousCity
                        .kmsToNext(Integer.parseInt(data));
            }

        }
    }

    private boolean isCity(String data) {
        return !(data.chars().allMatch(Character::isDigit));
    }


    public void printMap() {
        City city = firstCity;
        while (City.NO_CITY != city) {
            System.out.println(city);
            city = city.next();
        }
    }

    public void parseMap(String map) {


    }

    public void putCity(String city) {

    }

    public void putKm(int km) {

    }


}
