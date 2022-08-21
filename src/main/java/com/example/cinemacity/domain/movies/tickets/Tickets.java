package com.example.cinemacity.domain.movies.tickets;

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

    @Column(name = "rowNumber", nullable = false)
    private String rowNumber;

    @Column(name = "seatNumber", nullable = false)
    private String seatNumber;

    @Column(name = "cinema_room_no", nullable = false)
    private String cinemaRoomNo;

}
