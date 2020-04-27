package com.flight.ticket.service;

import com.flight.ticket.constants.GlobalConstants;
import com.flight.ticket.entity.Flight;
import com.flight.ticket.entity.Ticket;
import com.flight.ticket.filter.TicketFilter;
import com.flight.ticket.repository.FlightRepository;
import com.flight.ticket.repository.TicketRepository;
import com.flight.ticket.service.base.ServiceResult;
import com.flight.ticket.data.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Burak Naroglu
 */

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@Transactional
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;


    @Before
    public void init() {

        flightRepository.deleteAll();
        ticketRepository.deleteAll();

        flightRepository.saveAndFlush(TestData.createFlights().get(0));
        flightRepository.saveAndFlush(TestData.createFlights().get(1));

        Flight flight = flightRepository.findAll().stream().findFirst().get();

        Ticket firstTicket = TestData.createTickets().get(0);
        firstTicket.setFlight(flight);
        ticketRepository.save(firstTicket);

        Ticket secondTicket = TestData.createTickets().get(1);
        secondTicket.setFlight(flight);
        ticketRepository.save(secondTicket);
    }

    @Test
    public void findAll_Success() {
        ServiceResult<List<Ticket>> response = ticketService.findAll();
        Assert.assertEquals(2, response.getResult().size());
    }

    @Test
    public void filter_Success() {
        TicketFilter ticketFilter = new TicketFilter();
        ticketFilter.setNumber(1234);
        ticketFilter.setFlightName("istanbul");
        ServiceResult<List<Ticket>> response = ticketService.filter(ticketFilter);
        Assert.assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test
    public void filterCheckNumber_Success() {
        TicketFilter ticketFilter = new TicketFilter();
        ticketFilter.setNumber(0);
        ServiceResult<List<Ticket>> response = ticketService.filter(ticketFilter);
        Assert.assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test
    public void save_Success() {
        ServiceResult<Ticket> response = ticketService.save(TestData.createTickets().get(0));
        Assert.assertEquals("1234", response.getResult().getNumber().toString());
    }

    @Test
    public void availableTickets() {
        ServiceResult<List<Ticket>> response = ticketService.availableTickets();
        Assert.assertEquals(2, response.getResult().size());
    }

    @Test
    public void availableTickets_NotFound() {
        ticketRepository.deleteAll();
        flightRepository.deleteAll();
        ServiceResult<List<Ticket>> response = ticketService.availableTickets();
        Assert.assertEquals(GlobalConstants.NOT_FOUND_MESSAGE, response.getResult());
    }

    @Test
    public void cancelTicket() {
        List<Ticket> tickets = ticketRepository.findAll();
        ServiceResult<Ticket> response = ticketService.cancelTicket(tickets.stream().findAny().get().getId());
        Assert.assertEquals(GlobalConstants.SUCCESS_MESSAGE, response.getResult());
    }

    @Test
    public void cancelTicket_Exception() {
        ServiceResult<Ticket> response = ticketService.cancelTicket(null);
        Assert.assertEquals(GlobalConstants.NOT_FOUND_MESSAGE, response.getResult());
    }

    @Test
    public void createTicket() {
        Ticket response = ticketService.createTicket(TestData.createFlights().stream().findFirst().get(), 15);
        Assert.assertEquals(TestData.createFlights().stream().findFirst().get().getName(), response.getFlight().getName());
    }

    @Test
    public void calculatePrice() {
        Double response = ticketService.calculatePrice(10, 100, 10.0);
        Assert.assertEquals("11.0", response.toString());
    }

}

