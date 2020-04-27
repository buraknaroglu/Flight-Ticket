package com.flight.ticket.controller;

import com.flight.ticket.entity.Route;
import com.flight.ticket.filter.RouteFilter;
import com.flight.ticket.repository.RouteRepository;
import com.flight.ticket.service.RouteService;
import com.flight.ticket.data.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Burak Naroglu
 */

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@Transactional
public class RouteControllerTest {

    @Autowired
    private RouteController routeController;

    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteRepository routeRepository;


    @Before
    public void init() {
        routeRepository.deleteAll();
        routeService.save(TestData.createRoutes().get(0));
        routeService.save(TestData.createRoutes().get(1));
    }

    @Test
    public void findAll_Success() {
        ResponseEntity<List<Route>> response = routeController.findAll();
        Assert.assertEquals(2, response.getBody().size());
    }

    @Test
    public void filter_Success() {
        RouteFilter routeFilter = new RouteFilter();
        routeFilter.setName("es");
        ResponseEntity<List<Route>> response = routeController.filter(routeFilter);
        Assert.assertEquals("Eskişehir Route", response.getBody().stream().findFirst().get().getName());
    }

}

