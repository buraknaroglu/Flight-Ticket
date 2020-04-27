package com.flight.ticket.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Burak Naroglu
 */

@Getter
@Setter
@Entity(name = "Flight")
@Table(name = "FLIGHT")
public class Flight {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "GEN_FLIGHT", sequenceName = "SEQ_FLIGHT", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CAPACITY")
    private Integer capacity;

    @Column(name = "PRICE")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "AIRLINE_ID")
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "ROUTE_ID")
    private Route route;

}

    