package com.flight.ticket.service;

import com.flight.ticket.filter.RouteFilter;
import com.flight.ticket.repository.RouteRepository;
import com.flight.ticket.service.base.BaseService;
import com.flight.ticket.specification.RouteFilterSpecification;
import com.flight.ticket.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Burak Naroglu
 */

@Service
public class RouteService extends BaseService<Route, RouteFilter, RouteFilterSpecification> {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private RouteFilterSpecification routeFilterSpecification;


    @Override
    public RouteRepository getRepository() {
        return routeRepository;
    }

    @Override
    public RouteFilterSpecification getSpecification() {
        return routeFilterSpecification;
    }

}

    