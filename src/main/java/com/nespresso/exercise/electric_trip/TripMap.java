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
                        .isNextOf(previousCity);
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
        // TODO : Warning a Getter
        City startingCity = findCity(participant.getStartLocation());
        double maxDistance = participant.calculateMaxDistWithLowSpeed();

        City city = startingCity;
        while (city.next() != City.NO_CITY && maxDistance > city.getKmsToNextCity()) {
            maxDistance = maxDistance - city.getKmsToNextCity();
            city = city.next();
        }

        participant.setCurrentChargeInKw(maxDistance / participant.getLowSpeedPerformance());
        participant.setCurrentLocation(city);

        //participant.setCurrentCharge(Math.round(maxDistance / participant.calculateMaxDistWithLowSpeed() * 100));

    }


    public void sprintUtMost(Participant participant) {
        // TODO : Warning a Getter
        City startingCity = findCity(participant.getStartLocation());
        // There is an error here !!!
        double maxDistance = participant.calculateMaxDistWithHighSpeed();

        City city = startingCity;
        while (city.next() != City.NO_CITY && maxDistance > city.getKmsToNextCity()) {
            maxDistance = maxDistance - city.getKmsToNextCity();
            city = city.next();
        }


        participant.setCurrentChargeInKw(maxDistance / participant.getHighSpeedPerformance());
        participant.setCurrentLocation(city);

      //  participant.setCurrentCharge(Math.round(maxDistance / participant.calculateMaxDistWithHighSpeed() * 100));

       // participant.setCurrentChargeInKw(maxDistance / participant.getHighSpeedPerformance());
        participant.setCurrentLocation(city);
    }

    public City findCity(String city) {

        return cities.stream()
                .filter(c -> c.hasName(city))
                .collect(Collectors.toList()).get(0);
    }

    public void charge(Participant participant, int hoursOfCharge) {

       // participant

    }

    public void parseMap(String map) {


    }

    public void putCity(String city) {

    }

    public void putKm(int km) {

    }



}
