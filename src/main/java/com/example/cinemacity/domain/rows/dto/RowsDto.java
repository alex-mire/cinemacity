package com.example.cinemacity.domain.rows.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RowsDto {
    private int cinemaRoomNumber;
    private int rowNumber;
    private int seatsNumber;
}
