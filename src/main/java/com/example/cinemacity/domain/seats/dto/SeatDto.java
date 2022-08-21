package com.example.cinemacity.domain.seats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {
    private Integer cinemaRoomNumber;
    private Integer rowNumber;
    private Integer seatsNumber;

}
