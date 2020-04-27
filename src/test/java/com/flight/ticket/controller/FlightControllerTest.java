package com.flight.ticket.controller;

import com.flight.ticket.entity.Airline;
import com.flight.ticket.entity.Flight;
import com.flight.ticket.filter.FlightFilter;
import com.flight.ticket.repository.AirlineRepository;
import com.flight.ticket.repository.FlightRepository;
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
public class FlightControllerTest {

    @Autowired
    private FlightController flightController;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineRepository airlineRepository;


    @Before
    public void init() {
        flightRepository.deleteAll();
        airlineRepository.deleteAll();
        Airline airline = airlineRepository.save(TestData.createAirlines().get(0));
        Flight flight = TestData.createFlights().get(0);
        flight.setAirline(airline);
        flightRepository.save(flight);
        flightRepository.save(TestData.createFlights().get(1));
    }

    @Test
    public void findAll_Success() {
        ResponseEntity<List<Flight>> response = flightController.findAll();
        Assert.assertEquals(2, response.getBody().size());
    }

    @Test
    public void filter_Success() {
        FlightFilter flightFilter = new FlightFilter();
        flightFilter.setName("tan");
        flightFilter.setAirlineName("turk");
        ResponseEntity<List<Flight>> response = flightController.filter(flightFilter);
        Assert.assertEquals("İstanbul Flight", response.getBody().stream().findFirst().get().getName());
    }

}

