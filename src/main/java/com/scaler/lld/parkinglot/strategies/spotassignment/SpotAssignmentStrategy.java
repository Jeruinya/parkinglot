package com.scaler.lld.parkinglot.strategies.spotassignment;

import com.scaler.lld.parkinglot.models.Gate;
import com.scaler.lld.parkinglot.models.ParkingSpot;
import com.scaler.lld.parkinglot.models.VehicleType;

public interface SpotAssignmentStrategy {
    ParkingSpot assignSpot(VehicleType vehicleType, Gate gate);
}
