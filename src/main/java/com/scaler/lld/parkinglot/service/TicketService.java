package com.scaler.lld.parkinglot.service;

import java.util.Date;

import com.scaler.lld.parkinglot.exceptions.NoParkingSpotAvailableException;
import com.scaler.lld.parkinglot.models.Gate;
import com.scaler.lld.parkinglot.models.ParkingSpot;
import com.scaler.lld.parkinglot.models.Ticket;
import com.scaler.lld.parkinglot.models.Vehicle;
import com.scaler.lld.parkinglot.models.VehicleType;
import com.scaler.lld.parkinglot.repositories.TicketRepository;
import com.scaler.lld.parkinglot.strategies.spotassignment.SpotAssignmentStrategy;

public class TicketService {
    private VehicleService vehicleService;
    private GateService gateService;
    private SpotAssignmentStrategy spotAssignmentStrategy;
    private TicketRepository ticketRepository;

    public TicketService(VehicleService vehicleService, GateService gateService,
                         SpotAssignmentStrategy spotAssignmentStrategy, TicketRepository ticketRepository) {
        this.vehicleService = vehicleService;
        this.gateService = gateService;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.ticketRepository = ticketRepository;
    }
    public Ticket generateTicket(String vehicleNumber, VehicleType vehicleType, Long gateId) throws NoParkingSpotAvailableException {
        // Check if this vehicle is there in DB
        //    1. VehicleService.getVehicleByNumber() - Approach
        //    2. VehicleRepository.fetchByNumber()
        // If yes, get vehicle object
        // Else, create a vehicle
        // Create a Ticket

        Vehicle vehicle = vehicleService.getVehicle(vehicleNumber);

        if (vehicle == null) {
            vehicle = vehicleService.registerVehicle(vehicleNumber, vehicleType);
        }

        Gate gate = gateService.getGate(gateId);

        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(new Date());
        ticket.setOperator(gate.getOperator());
        ticket.setGate(gate);

        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(vehicleType, gate);

        if (parkingSpot == null) {
            throw new NoParkingSpotAvailableException("No available slot");
        }

        ticket.setParkingSpot(parkingSpot);

        Ticket savedTicket = ticketRepository.save(ticket);

        return savedTicket;
    }
}

// Break till 8: 54
// 0. Map for Repository
// 1. Throw and catch exception
// 2. Implement Spot Assignment strategy
// 3. Implement dependency injection

//      A
// |    |   |
// B    C   D
//    |    |
//    E    F
// Dependency Graph -> Topologically Sort -> create objects in order