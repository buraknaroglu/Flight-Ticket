package com.flight.ticket.data;

import com.flight.ticket.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Burak Naroglu
 */

public class TestData {

    public static List<Airline> createAirlines() {

        List<Airline> airlines = new ArrayList<>();

        Airline firstAirline = new Airline();
        firstAirline.setName("Turk hava yolları");
        airlines.add(firstAirline);

        Airline secondAirline = new Airline();
        secondAirline.setName("Pegasus");
        airlines.add(secondAirline);

        return airlines;
    }

    public static List<Airport> createAirports() {

        List<Airport> airports = new ArrayList<>();

        Airport firstAirport = new Airport();
        firstAirport.setName("İstanbul havalimanı");
        airports.add(firstAirport);

        Airport secondAirport = new Airport();
        secondAirport.setName("Eskişehir havalimanı");
        airports.add(secondAirport);

        return airports;
    }

    public static List<Route> createRoutes() {

        List<Route> routes = new ArrayList<>();

        Route firstRoute = new Route();
        firstRoute.setName("İstanbul Route");
        routes.add(firstRoute);

        Route secondRoute = new Route();
        secondRoute.setName("Eskişehir Route");
        routes.add(secondRoute);

        return routes;
    }

    public static List<Flight> createFlights() {

        List<Flight> flights = new ArrayList<>();

        Flight firstFlight = new Flight();
        firstFlight.setId(1L);
        firstFlight.setName("İstanbul Flight");
        firstFlight.setCapacity(100);
        firstFlight.setPrice(10.0);
        flights.add(firstFlight);

        Flight secondFlight = new Flight();
        firstFlight.setId(2L);
        secondFlight.setName("Eskişehir Flight");
        secondFlight.setCapacity(50);
        secondFlight.setPrice(100.0);
        flights.add(secondFlight);

        return flights;
    }

    public static List<Ticket> createTickets() {

        List<Ticket> tickets = new ArrayList<>();

        Ticket firstTicket = new Ticket();
        firstTicket.setId(3L);
        firstTicket.setNumber(123);
        firstTicket.setPrice(10.0);
        tickets.add(firstTicket);

        Ticket secondTicket = new Ticket();
        firstTicket.setNumber(1234);
        firstTicket.setPrice(11.1);
        tickets.add(secondTicket);

        return tickets;
    }

}

