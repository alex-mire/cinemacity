package com.example.cinemacity.domain.tickets;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tickets")
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "row_id", nullable = false)
    private Long rowId;

    @Column(name = "seat_id", nullable = false)
    private Long seatId;

    @Column(name = "room_id", nullable = false)
    private Long roomId;

    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "starts_at", nullable = false)
    private LocalDateTime startsAt;


}
