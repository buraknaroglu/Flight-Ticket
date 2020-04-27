package com.flight.ticket.controller;

import com.flight.ticket.specification.RouteFilterSpecification;
import com.flight.ticket.entity.Route;
import com.flight.ticket.filter.RouteFilter;
import com.flight.ticket.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Burak Naroglu
 */

@RestController
@RequestMapping("routes")
public class RouteController extends BaseController<Route, RouteFilter, RouteFilterSpecification, RouteService> {

    @Autowired
    private RouteService routeService;

    @Override
    protected RouteService getService() {
        return routeService;
    }

}

    