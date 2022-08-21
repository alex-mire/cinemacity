package com.example.cinemacity.domain.CinemaRoom;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="cinema_room")
public class CinemaRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name="room_number", nullable = false)
    private Integer roomNumber;

    @Column(name="rows_number", nullable = false)
    private Integer rowsNumber;

    @Column(name="seats_number")
    private Integer seatsNumber;
}
