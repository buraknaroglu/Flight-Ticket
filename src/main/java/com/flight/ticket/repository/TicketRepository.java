package com.flight.ticket.repository;

import com.flight.ticket.entity.Ticket;
import org.springframework.stereotype.Repository;

/**
 * @author Burak Naroglu
 */

@Repository
public interface TicketRepository extends BaseRepository<Ticket> {

    Integer countAllByFlightId(Long flightId);

}

    