package com.flight.ticket.service;

import com.flight.ticket.entity.Airline;
import com.flight.ticket.filter.AirlineFilter;
import com.flight.ticket.repository.AirlineRepository;
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
public class AirlineServiceTest {

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private AirlineRepository airlineRepository;


    @Before
    public void init() {
        airlineRepository.deleteAll();
        airlineRepository.save(TestData.createAirlines().get(0));
        airlineRepository.save(TestData.createAirlines().get(1));
    }

    @Test
    public void findAll_Success() {
        ServiceResult<List<Airline>> response = airlineService.findAll();
        Assert.assertEquals(2, response.getResult().size());
    }

    @Test
    public void filter_Success() {
        AirlineFilter airlineFilter = new AirlineFilter();
        airlineFilter.setName("e");
        ServiceResult<List<Airline>> response = airlineService.filter(airlineFilter);
        Assert.assertEquals("Pegasus", response.getResult().stream().findFirst().get().getName());
    }

    @Test
    public void save_Success() {
        ServiceResult<Airline> response = airlineService.save(TestData.createAirlines().get(0));
        Assert.assertEquals("Turk hava yolları", response.getResult().getName());
    }

}

