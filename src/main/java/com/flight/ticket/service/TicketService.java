package com.flight.ticket.service;

import com.flight.ticket.repository.TicketRepository;
import com.flight.ticket.service.base.BaseService;
import com.flight.ticket.service.base.ServiceResult;
import com.flight.ticket.specification.TicketFilterSpecification;
import com.flight.ticket.constants.GlobalConstants;
import com.flight.ticket.entity.Flight;
import com.flight.ticket.entity.Ticket;
import com.flight.ticket.filter.TicketFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Burak Naroglu
 */

@Slf4j
@Service
public class TicketService extends BaseService<Ticket, TicketFilter, TicketFilterSpecification> {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketFilterSpecification ticketFilterSpecification;

    @Autowired
    private FlightService flightService;


    @Override
    public TicketRepository getRepository() {
        return ticketRepository;
    }

    @Override
    public TicketFilterSpecification getSpecification() {
        return ticketFilterSpecification;
    }

    public ServiceResult availableTickets() {
        List<Flight> flights = flightService.findAll().getResult();

        if (CollectionUtils.isEmpty(flights)) {
            log.info("Flight not found.");
            return new ServiceResult(GlobalConstants.NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
        }

        List<Ticket> tickets = new ArrayList<>();

        flights.forEach(flight -> {
            Integer existTicketAmount = getRepository().countAllByFlightId(flight.getId());
            if (flight.getCapacity() > existTicketAmount) {
                Ticket ticket = createTicket(flight, existTicketAmount);
                tickets.add(ticket);
            }
        });
        return new ServiceResult(tickets);
    }

    public Ticket createTicket(Flight flight, Integer existTicketAmount) {
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setNumber((int) (Math.random() * 10000));
        ticket.setPrice(calculatePrice(existTicketAmount, flight.getCapacity(), flight.getPrice()));
        return ticket;
    }

    public Double calculatePrice(Integer existTicketAmount, Integer capacity, Double price) {
        int percentage = (existTicketAmount * 10) / capacity;

        if (percentage >= 1) {
            for (int i = 0; i < percentage; i++) {
                price += price / 10;
            }
        }
        return price;
    }

    public ServiceResult cancelTicket(Long ticketId) {
        try {
            getRepository().deleteById(ticketId);
            return new ServiceResult(GlobalConstants.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("cancelTicket got exception. ticketId : {}", ticketId);
            return new ServiceResult(GlobalConstants.NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
        }
    }

}

    