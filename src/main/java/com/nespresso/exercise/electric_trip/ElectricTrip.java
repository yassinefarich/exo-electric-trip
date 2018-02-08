package com.nespresso.exercise.electric_trip;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class ElectricTrip {


    List<Participant> participants;

    private TripMap tripMap;// TODO : Dont call this class Map !!!!
    private String destination;


    public static void main(String[] args) {
        ElectricTrip trip = new ElectricTrip("PARIS-200-BOURGES");
    }

    public ElectricTrip(String tripMapInfo) {
        tripMap = new TripMap(tripMapInfo);
        participants = new ArrayList<>();
        tripMap.printMap();
    }

    public int startTripIn(String start, int batterySize, int lowSpeedPerformance, int highSpeedPerformance) {

        participants.add(Participant.builder()
                .batterySize(batterySize)
                .highSpeedPerformance(highSpeedPerformance)
                .lowSpeedPerformance(lowSpeedPerformance)
                .location(tripMap.findCity(start))
                .build()
        );
        return participants.size() - 1;
    }

    public void go(int participantId) {
        Participant participant = participants.get(participantId);
        tripMap.goUtMost(participant);
    }

    public void sprint(int participantId) {
        Participant participant = participants.get(participantId);
        tripMap.sprintUtMost(participant);
    }

    public void charge(int participantId, int hoursOfCharge) {
        Participant participant = participants.get(participantId);
        participant.charge(hoursOfCharge);

    }

    public String locationOf(int participantId) {
        return participants.get(participantId).getLocation().getName();
    }

    public String chargeOf(int participantId) {
        return participants.get(participantId).calculateBatteryCharge() + "%";
    }

}
