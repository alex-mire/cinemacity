package com.example.cinemacity.domain.CinemaRoom;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="cinema_room")
public class CinemaRoom {
    @Id
    private String id;

    @Column(name="room_number", nullable = false)
    private String roomNumber;

    @Column(name="room_rows", nullable = false)
    private String room_rows;

    @Column(name="seats_number")
    private String seats_number;
}
