package com.scaler.lld.parkinglot.strategies.feecalculation;

import com.scaler.lld.parkinglot.models.Ticket;

public interface FeesCalculationStrategy {
    int calculateFees(Ticket ticket);
}
