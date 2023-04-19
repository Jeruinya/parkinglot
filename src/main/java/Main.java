import com.scaler.lld.parkinglot.controller.TicketController;
import com.scaler.lld.parkinglot.dto.GenerateTicketRequestDto;
import com.scaler.lld.parkinglot.dto.GenerateTicketResponseDto;
import com.scaler.lld.parkinglot.models.VehicleType;
import com.scaler.lld.parkinglot.repositories.ParkingLotRepository;
import com.scaler.lld.parkinglot.repositories.TicketRepository;
import com.scaler.lld.parkinglot.service.GateService;
import com.scaler.lld.parkinglot.service.TicketService;
import com.scaler.lld.parkinglot.service.VehicleService;
import com.scaler.lld.parkinglot.strategies.spotassignment.RandomSpotAssignmentStrategy;
import com.scaler.lld.parkinglot.strategies.spotassignment.SpotAssignmentStrategy;

public class Main {
    public static void main(String[] args) {
        // Instantiate controllers, service etc and inject dependencies into them

        ObjectRegistry objectRegistry = new ObjectRegistry();
        VehicleService vehicleService = new VehicleService();
        GateService gateService = new GateService();
        TicketRepository ticketRepository = new TicketRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        SpotAssignmentStrategy spotAssignmentStrategy = new RandomSpotAssignmentStrategy(parkingLotRepository);
        TicketService ticketService = new TicketService(
          vehicleService, gateService, spotAssignmentStrategy, ticketRepository
        );
        TicketController ticketController = new TicketController(ticketService);

        objectRegistry.register("ticketRepository", ticketRepository);

        ///

        GenerateTicketRequestDto request = new GenerateTicketRequestDto();
        request.setVehicleNumber("HR05VXXXX");
        request.setVehicleType(VehicleType.LARGE);
        request.setGateId(1L);

        GenerateTicketResponseDto response = ticketController.generateTicket(request);

        // Do whatever you want to do based on response
    }
}

// Requirements:
// 1. Operator should be able to generate a ticket
// 2. Admin should be able to create a new parking lot

// MVC
// Controller
// Service
// Repository

// 1. Identify which model is the requirement around (CRUD)
//     - Ticket
// 2. Create controller, service and repository for that model if not already existing
//     - TicketController
//     - TicketService
//     - TicketRepository

// Assignment:
// Implement following requirements:
// 1. Create a ParkingLot
// 2. Add gates to a ParkingLot
// 3. Create an operator
// 4. Assign operator to a gate
// 5. [BUGFIX] ParkingSpot should become unavailable on assignment of ticket