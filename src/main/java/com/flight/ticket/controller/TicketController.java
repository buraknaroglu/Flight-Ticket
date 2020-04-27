package com.flight.ticket.controller;

import com.flight.ticket.service.TicketService;
import com.flight.ticket.specification.TicketFilterSpecification;
import com.flight.ticket.entity.Ticket;
import com.flight.ticket.filter.TicketFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Burak Naroglu
 */

@RestController
@RequestMapping("tickets")
public class TicketController extends BaseController<Ticket, TicketFilter, TicketFilterSpecification, TicketService> {

    @Autowired
    private TicketService ticketService;

    @Override
    protected TicketService getService() {
        return ticketService;
    }

    @GetMapping("available")
    public ResponseEntity availableTickets() {
        return createResponse(ticketService.availableTickets());
    }

    @DeleteMapping("cancel/{ticketId}")
    public ResponseEntity cancelTicket(@PathVariable Long ticketId) {
        return createResponse(ticketService.cancelTicket(ticketId));
    }

}

    