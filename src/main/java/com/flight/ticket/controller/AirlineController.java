package com.flight.ticket.controller;

import com.flight.ticket.specification.AirlineFilterSpecification;
import com.flight.ticket.entity.Airline;
import com.flight.ticket.filter.AirlineFilter;
import com.flight.ticket.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Burak Naroglu
 */

@RestController
@RequestMapping("airlines")
public class AirlineController extends BaseController<Airline, AirlineFilter, AirlineFilterSpecification, AirlineService> {

    @Autowired
    private AirlineService airlineService;

    @Override
    protected AirlineService getService() {
        return airlineService;
    }

}

    