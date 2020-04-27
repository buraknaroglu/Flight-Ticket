package com.flight.ticket.controller;

import com.flight.ticket.entity.Airport;
import com.flight.ticket.filter.AirportFilter;
import com.flight.ticket.repository.AirportRepository;
import com.flight.ticket.service.AirportService;
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
public class AirportControllerTest {

    @Autowired
    private AirportController airportController;

    @Autowired
    private AirportService airportService;

    @Autowired
    private AirportRepository airportRepository;


    @Before
    public void init() {
        airportRepository.deleteAll();
        airportService.save(TestData.createAirports().get(0));
        airportService.save(TestData.createAirports().get(1));
    }

    @Test
    public void findAll_Success() {
        ResponseEntity<List<Airport>> response = airportController.findAll();
        Assert.assertEquals(2, response.getBody().size());
    }

    @Test
    public void filter_Success() {
        AirportFilter airportFilter = new AirportFilter();
        airportFilter.setName("e");
        ResponseEntity<List<Airport>> response = airportController.filter(airportFilter);
        Assert.assertEquals("Eskişehir havalimanı", response.getBody().stream().findFirst().get().getName());
    }

}

