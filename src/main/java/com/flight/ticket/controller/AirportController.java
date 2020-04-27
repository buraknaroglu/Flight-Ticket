package com.flight.ticket.controller;

import com.flight.ticket.specification.AirportFilterSpecification;
import com.flight.ticket.entity.Airport;
import com.flight.ticket.filter.AirportFilter;
import com.flight.ticket.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Burak Naroglu
 */

@RestController
@RequestMapping("airports")
public class AirportController extends BaseController<Airport, AirportFilter, AirportFilterSpecification, AirportService> {

    @Autowired
    private AirportService airportService;

    @Override
    protected AirportService getService() {
        return airportService;
    }

}

    