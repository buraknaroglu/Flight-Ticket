package com.flight.ticket.controller;

import com.flight.ticket.constants.GlobalConstants;
import com.flight.ticket.entity.Flight;
import com.flight.ticket.entity.Ticket;
import com.flight.ticket.filter.TicketFilter;
import com.flight.ticket.repository.FlightRepository;
import com.flight.ticket.repository.TicketRepository;
import com.flight.ticket.data.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
public class TicketControllerTest {

    @Autowired
    private TicketController ticketController;

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
        ResponseEntity<List<Ticket>> response = ticketController.findAll();
        Assert.assertEquals(2, response.getBody().size());
    }

    @Test
    public void filter_Success() {
        TicketFilter ticketFilter = new TicketFilter();
        ticketFilter.setNumber(1234);
        ticketFilter.setFlightName("istanbul");
        ResponseEntity<List<Ticket>> response = ticketController.filter(ticketFilter);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void filterCheckNumber_Success() {
        TicketFilter ticketFilter = new TicketFilter();
        ticketFilter.setNumber(0);
        ResponseEntity<List<Ticket>> response = ticketController.filter(ticketFilter);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void availableTickets() {
        ResponseEntity<List<Ticket>> response = ticketController.availableTickets();
        Assert.assertEquals(2, response.getBody().size());
    }

    @Test
    public void availableTickets_NotFound() {
        ticketRepository.deleteAll();
        flightRepository.deleteAll();
        ResponseEntity<List<Ticket>> response = ticketController.availableTickets();
        Assert.assertEquals(GlobalConstants.NOT_FOUND_MESSAGE, response.getBody());
    }

    @Test
    public void cancelTicket() {
        List<Ticket> tickets = ticketRepository.findAll();
        ResponseEntity<Ticket> response = ticketController.cancelTicket(tickets.stream().findFirst().get().getId());
        Assert.assertEquals(GlobalConstants.SUCCESS_MESSAGE, response.getBody());
    }

    @Test
    public void cancelTicket_Exception() {
        ResponseEntity<Ticket> response = ticketController.cancelTicket(null);
        Assert.assertEquals(GlobalConstants.NOT_FOUND_MESSAGE, response.getBody());
    }

}

