package com.flight.ticket.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Burak Naroglu
 */

@Getter
@Setter
@Entity(name = "Airline")
@Table(name = "AIRLINE")
public class Airline {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "GEN_AIRLINE", sequenceName = "SEQ_AIRLINE", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

}

    