package com.scaler.lld.parkinglot.repositories;

import java.util.HashMap;
import java.util.Map;

import com.scaler.lld.parkinglot.models.Ticket;

public class TicketRepository {
    private Map<Long, Ticket> tickets = new HashMap<>();
    private Long idSequence = 0L;
    public Ticket save(Ticket ticket) {
        idSequence += 1;
        ticket.setId(idSequence);
        tickets.put(idSequence, ticket);

        return ticket;
    }
}
