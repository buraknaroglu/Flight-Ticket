package com.flight.ticket.service;

import com.flight.ticket.entity.Airline;
import com.flight.ticket.entity.Flight;
import com.flight.ticket.filter.FlightFilter;
import com.flight.ticket.repository.AirlineRepository;
import com.flight.ticket.repository.FlightRepository;
import com.flight.ticket.service.base.ServiceResult;
import com.flight.ticket.data.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Burak Naroglu
 */

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@Transactional
public class FlightServiceTest {

    @Autowired
    private FlightService flightService;

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
        ServiceResult<List<Flight>> response = flightService.findAll();
        Assert.assertEquals(2, response.getResult().size());
    }

    @Test
    public void filter_Success() {
        FlightFilter flightFilter = new FlightFilter();
        flightFilter.setName("tan");
        flightFilter.setAirlineName("turk");
        ServiceResult<List<Flight>> response = flightService.filter(flightFilter);
        Assert.assertEquals("İstanbul Flight", response.getResult().stream().findFirst().get().getName());
    }

    @Test
    public void save_Success() {
        ServiceResult<Flight> response = flightService.save(TestData.createFlights().get(0));
        Assert.assertEquals("İstanbul Flight", response.getResult().getName());
    }

}

