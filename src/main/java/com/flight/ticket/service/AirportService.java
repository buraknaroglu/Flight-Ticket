package com.flight.ticket.service;

import com.flight.ticket.repository.AirportRepository;
import com.flight.ticket.service.base.BaseService;
import com.flight.ticket.specification.AirportFilterSpecification;
import com.flight.ticket.entity.Airport;
import com.flight.ticket.filter.AirportFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Burak Naroglu
 */

@Service
public class AirportService extends BaseService<Airport, AirportFilter, AirportFilterSpecification> {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirportFilterSpecification airportFilterSpecification;


    @Override
    public AirportRepository getRepository() {
        return airportRepository;
    }

    @Override
    public AirportFilterSpecification getSpecification() {
        return airportFilterSpecification;
    }

}

    