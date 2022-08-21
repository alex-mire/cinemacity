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
    private String id;

    @Column(name="row_number", nullable = false)
    private Integer rowNumber;

    @Column(name="seat_number", nullable = false)
    private Integer seatNumber;

    @Column(name="cinema_room_number", nullable = false)
    private Integer cinemaRoomNumber;

    @Column(name="available")
    private Boolean available;
}
