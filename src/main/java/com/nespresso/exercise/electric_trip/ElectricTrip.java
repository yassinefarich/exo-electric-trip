package com.nespresso.exercise.electric_trip;

import java.util.ArrayList;
import java.util.List;

public class ElectricTrip {

    private List<Participant> participants;

    private TripPlan tripPlan;

    public ElectricTrip(String tripMapInfo) {
        tripPlan = TripPlan.newFromPlan(tripMapInfo);
        participants = new ArrayList<>();
    }

    public int startTripIn(String start, int batterySize, int lowSpeedPerformance, int highSpeedPerformance) {

        participants.add(Participant.builder()
                .batterySize(batterySize)
                .currentChargeInKw(batterySize)
                .highSpeedPerformance(highSpeedPerformance)
                .lowSpeedPerformance(lowSpeedPerformance)
                .location(tripPlan.findCity(start))
                .build()
        );
        return participants.size() - 1;
    }

    public void go(int participantId) {
        Participant participant = participants.get(participantId);
        participant.go();
    }

    public void sprint(int participantId) {
        Participant participant = participants.get(participantId);
        participant.sprint();
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
