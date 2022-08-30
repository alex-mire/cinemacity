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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="room_number", nullable = false)
    private int roomNumber;

    @Column(name="rows_number", nullable = false)
    private int rowsNumber;

    @Column(name="seats_number")
    private int seatsNumber;
}
