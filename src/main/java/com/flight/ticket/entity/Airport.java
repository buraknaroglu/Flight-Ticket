package com.flight.ticket.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Burak Naroglu
 */

@Getter
@Setter
@Entity(name = "Airport")
@Table(name = "AIRPORT")
public class Airport {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "GEN_AIRPORT", sequenceName = "SEQ_AIRPORT", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

}

    