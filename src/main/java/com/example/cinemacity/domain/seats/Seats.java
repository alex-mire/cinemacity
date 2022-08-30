package com.example.cinemacity.domain.seats;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="seats")
public class Seats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="row_number", nullable = false)
    private int rowNumber;

    @Column(name="seat_number", nullable = false)
    private int seatNumber;

    @Column(name="cinema_room_number", nullable = false)
    private int cinemaRoomNumber;

    @Column(name="available")
    private boolean available;
}
