package com.flight.ticket.service;

import com.flight.ticket.filter.FlightFilter;
import com.flight.ticket.repository.FlightRepository;
import com.flight.ticket.service.base.BaseService;
import com.flight.ticket.specification.FlightFilterSpecification;
import com.flight.ticket.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Burak Naroglu
 */

@Service
public class FlightService extends BaseService<Flight, FlightFilter, FlightFilterSpecification> {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightFilterSpecification flightFilterSpecification;


    @Override
    public FlightRepository getRepository() {
        return flightRepository;
    }

    @Override
    public FlightFilterSpecification getSpecification() {
        return flightFilterSpecification;
    }

}

    