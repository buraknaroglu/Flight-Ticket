package com.flight.ticket.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Burak Naroglu
 */

@Getter
@Setter
@Entity(name = "Route")
@Table(name = "ROUTE")
public class Route {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "GEN_ROUTE", sequenceName = "SEQ_ROUTE", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToOne
    @JoinColumn(name = "AIRPORT_ID")
    private Airport airport;

}

    