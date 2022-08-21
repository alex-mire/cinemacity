package com.example.cinemacity.domain.CinemaRoom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CinemaRoomDto {
    private Integer roomNumber;
    private Integer rowsNumber;
    private Integer seatsNumber;
}
