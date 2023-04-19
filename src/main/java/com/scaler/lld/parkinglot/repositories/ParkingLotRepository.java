package com.scaler.lld.parkinglot.repositories;

import java.util.HashMap;
import java.util.Map;

import com.scaler.lld.parkinglot.models.Gate;
import com.scaler.lld.parkinglot.models.ParkingLot;

public class ParkingLotRepository {

    private Map<Long, ParkingLot> parkingLots = new HashMap<>();

    public ParkingLot getParkingLotWithGate(Gate gate) {
        for (ParkingLot parkingLot: parkingLots.values()) {
            if (parkingLot.getGates().contains(gate)) {
                return parkingLot;
            }
        }

        return null;
    }
}
