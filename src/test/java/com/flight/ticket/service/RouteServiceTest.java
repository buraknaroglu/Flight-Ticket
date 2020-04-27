package com.flight.ticket.service;

import com.flight.ticket.entity.Route;
import com.flight.ticket.filter.RouteFilter;
import com.flight.ticket.repository.RouteRepository;
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
public class RouteServiceTest {

    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteRepository routeRepository;


    @Before
    public void init() {
        routeRepository.deleteAll();
        routeRepository.save(TestData.createRoutes().get(0));
        routeRepository.save(TestData.createRoutes().get(1));
    }

    @Test
    public void findAll_Success() {
        ServiceResult<List<Route>> response = routeService.findAll();
        Assert.assertEquals(2, response.getResult().size());
    }

    @Test
    public void filter_Success() {
        RouteFilter routeFilter = new RouteFilter();
        routeFilter.setName("es");
        ServiceResult<List<Route>> response = routeService.filter(routeFilter);
        Assert.assertEquals("Eskişehir Route", response.getResult().stream().findFirst().get().getName());
    }

    @Test
    public void save_Success() {
        ServiceResult<Route> response = routeService.save(TestData.createRoutes().get(0));
        Assert.assertEquals("İstanbul Route", response.getResult().getName());
    }

}

