package com.flight.ticket.controller;

import com.flight.ticket.entity.Airline;
import com.flight.ticket.filter.AirlineFilter;
import com.flight.ticket.repository.AirlineRepository;
import com.flight.ticket.service.AirlineService;
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
public class AirlineControllerTest {

    @Autowired
    private AirlineController airlineController;

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private AirlineRepository airlineRepository;


    @Before
    public void init() {
        airlineRepository.deleteAll();
        airlineService.save(TestData.createAirlines().get(0));
        airlineService.save(TestData.createAirlines().get(1));
    }

    @Test
    public void findAll_Success() {
        ResponseEntity<List<Airline>> response = airlineController.findAll();
        Assert.assertEquals(2, response.getBody().size());
    }

    @Test
    public void filter_Success() {
        AirlineFilter airlineFilter = new AirlineFilter();
        airlineFilter.setName("e");
        ResponseEntity<List<Airline>> response = airlineController.filter(airlineFilter);
        Assert.assertEquals("Pegasus", response.getBody().stream().findFirst().get().getName());
    }

}

