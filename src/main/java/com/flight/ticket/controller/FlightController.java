package com.flight.ticket.controller;

import com.flight.ticket.specification.FlightFilterSpecification;
import com.flight.ticket.entity.Flight;
import com.flight.ticket.filter.FlightFilter;
import com.flight.ticket.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Burak Naroglu
 */

@RestController
@RequestMapping("flights")
public class FlightController extends BaseController<Flight, FlightFilter, FlightFilterSpecification, FlightService> {

    @Autowired
    private FlightService flightService;

    @Override
    protected FlightService getService() {
        return flightService;
    }

}

    