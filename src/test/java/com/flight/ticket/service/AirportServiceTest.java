package com.flight.ticket.service;

import com.flight.ticket.entity.Airport;
import com.flight.ticket.filter.AirportFilter;
import com.flight.ticket.repository.AirportRepository;
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
public class AirportServiceTest {

    @Autowired
    private AirportService airportService;

    @Autowired
    private AirportRepository airportRepository;


    @Before
    public void init() {
        airportRepository.deleteAll();
        airportRepository.save(TestData.createAirports().get(0));
        airportRepository.save(TestData.createAirports().get(1));
    }

    @Test
    public void findAll_Success() {
        ServiceResult<List<Airport>> response = airportService.findAll();
        Assert.assertEquals(2, response.getResult().size());
    }

    @Test
    public void filter_Success() {
        AirportFilter airportFilter = new AirportFilter();
        airportFilter.setName("e");
        ServiceResult<List<Airport>> response = airportService.filter(airportFilter);
        Assert.assertEquals("Eskişehir havalimanı", response.getResult().stream().findFirst().get().getName());
    }

    @Test
    public void save_Success() {
        ServiceResult<Airport> response = airportService.save(TestData.createAirports().get(0));
        Assert.assertEquals("İstanbul havalimanı", response.getResult().getName());
    }

}

