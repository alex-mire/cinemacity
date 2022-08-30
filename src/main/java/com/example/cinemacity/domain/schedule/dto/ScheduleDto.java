package com.example.cinemacity.domain.schedule.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleDto {
    private Long roomId;
    private Long movieId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
}
