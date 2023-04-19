package com.scaler.lld.parkinglot.strategies.spotassignment;

import com.scaler.lld.parkinglot.models.Gate;
import com.scaler.lld.parkinglot.models.ParkingSpot;
import com.scaler.lld.parkinglot.models.VehicleType;
import com.scaler.lld.parkinglot.repositories.ParkingLotRepository;

public class RandomSpotAssignmentStrategy implements SpotAssignmentStrategy {

    private ParkingLotRepository parkingLotRepository;

    public RandomSpotAssignmentStrategy(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public ParkingSpot assignSpot(VehicleType vehicleType, Gate gate) {
//        ParkingLot parkingLot = ParkingLotRepository.getParkingLotWithGate();
//        List<ParkingSpot> parkingSpots = ParkingSpotRepository.getParkingSpotsByLot();
//
//        for (ParkingSpot parkingSpot: parkingSpots) {
//            if (parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.AVAILABLE) && parkingSpot.getSupportedVehicleTypes().contains(vehicleType)) {
//                return parkingSpot;
//            }
//        }
//        return null;

        return null;
    }
}
