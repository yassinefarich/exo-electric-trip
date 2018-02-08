package com.nespresso.exercise.electric_trip;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ElectricTrip {


    private TripMap tripMap;
    private String sourceLocation;
    private String destination;

    private int batterySize; //KWh
    private int lowSpeedPerformance; //Km per KWh
    private int highSpeedPerformance; //Km per KWh



    public static void main(String[] args)
    {
        ElectricTrip trip = new ElectricTrip("PARIS-200-BOURGES");
    }

    public ElectricTrip(String tripMapInfo) {
        tripMap = new TripMap(tripMapInfo);
        tripMap.printMap();
    }

    public int startTripIn(String paris, int batterySize, int lowSpeedPerformance, int highSpeedPerformance) {
        throw new NotImplementedException();
    }

    public void go(int participantId) {
        throw new NotImplementedException();
    }

    public String locationOf(int participantId) {
        throw new NotImplementedException();
    }

    public String chargeOf(int participantId) {
        throw new NotImplementedException();
    }

}
