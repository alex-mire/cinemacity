package com.example.cinemacity.domain.tickets;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "tickets")
public class Tickets {

    @Id
    private String id;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "row", nullable = false)
    private String row;

    @Column(name = "seat", nullable = false)
    private String seat;

}
