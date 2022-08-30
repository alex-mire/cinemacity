package com.example.cinemacity.domain.tickets.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public class TicketsDto {
    private Long rowId;
    private Long seatId;
    private Long roomId;
    private Long movieId;
    private Long clientId;
    private LocalDateTime startsAt;
}
