package com.nespresso.exercise.electric_trip;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TripPlan {

    List<City> cities = new ArrayList<>();

    public static TripPlan newFromPlan(String plan) {
        return new TripPlan(plan);
    }

    private TripPlan(String tripMap) {
        parsePlan(tripMap);
    }

    private void parsePlan(String tripMap) {
        String[] mapData = tripMap.split("-");

        City city, previousCity = null;
        for (String data : mapData) {

            if (isCity(data)) {
                city = new City()
                        .withName(parseCityName(data))
                        .withChargerPower(parseChargerPower(data))
                        .withPreviousCity(previousCity);
                cities.add(city);
                previousCity = city;

            } else {
                previousCity
                        .kmsToNext(Integer.parseInt(data));
            }

        }
    }

    private int parseChargerPower(String data) {
        if (data.split(":").length > 1) {
            return Integer.parseInt(data.split(":")[1]);
        }
        return 0;
    }

    private String parseCityName(String data) {
        return data.split(":")[0];
    }


    private boolean isCity(String data) {
        return !(data.chars().allMatch(Character::isDigit));
    }


    public void printPlan() {
        cities.stream().forEach(System.out::println);
    }


    public City findCity(String city) {
        return cities.stream()
                .filter(c -> c.isNamed(city))
                .collect(Collectors.toList()).get(0);
    }


}
