package com.example.cinemacity.domain.rows;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "rows")
public class Rows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="cinema_room_number", nullable = false)
    private int cinemaRoomNumber;

    @Column(name = "row_number", nullable = false)
    private int rowNumber;

    @Column(name = "seats_number", nullable = false)
    private int seatsNumber;

}
