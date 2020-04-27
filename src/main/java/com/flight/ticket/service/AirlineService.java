package com.flight.ticket.service;

import com.flight.ticket.repository.AirlineRepository;
import com.flight.ticket.service.base.BaseService;
import com.flight.ticket.specification.AirlineFilterSpecification;
import com.flight.ticket.entity.Airline;
import com.flight.ticket.filter.AirlineFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Burak Naroglu
 */

@Service
public class AirlineService extends BaseService<Airline, AirlineFilter, AirlineFilterSpecification> {

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private AirlineFilterSpecification airlineFilterSpecification;


    @Override
    public AirlineRepository getRepository() {
        return airlineRepository;
    }

    @Override
    public AirlineFilterSpecification getSpecification() {
        return airlineFilterSpecification;
    }

}

    