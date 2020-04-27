package com.flight.ticket.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Burak Naroglu
 */

@Getter
@Setter
@Entity(name = "Ticket")
@Table(name = "TICKET")
public class Ticket {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "GEN_TICKET", sequenceName = "SEQ_TICKET", allocationSize = 1)
    private Long id;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "NUMBER")
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "FLIGHT_ID", nullable = false)
    private Flight flight;

}

    