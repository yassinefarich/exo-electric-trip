package com.nespresso.exercise.electric_trip;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TripMap {

    List<City> cities = new ArrayList<>();

    public TripMap(String tripMap) {
        String[] mapData = tripMap.split("-");

        City city = City.NO_CITY;
        City previousCity = City.NO_CITY;

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


    public void printMap() {
        cities.stream().forEach(System.out::println);
    }


    public void goUtMost(Participant participant) {

        City city = participant.getLocation();

        while (city.next() != City.NO_CITY && participant.doIHaveToGoTo(city)) {
            participant.go(city.getKmsToNextCity());
            city = city.next();
            participant.setLocation(city);
        }
    }


    public void sprintUtMost(Participant participant) {

        City city = participant.getLocation();

        while (city.next() != City.NO_CITY && participant.doIHaveToGoTo(city)) {
            participant.sprint(city.getKmsToNextCity());
            city = city.next();
            participant.setLocation(city);
        }
    }

    public City findCity(String city) {

        return cities.stream()
                .filter(c -> c.isNamed(city))
                .collect(Collectors.toList()).get(0);
    }


}
