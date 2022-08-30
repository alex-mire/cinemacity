package com.example.cinemacity.api.schedule;

import com.example.cinemacity.domain.CinemaRoom.CinemaRoom;
import com.example.cinemacity.domain.CinemaRoom.CinemaRoomRepository;
import com.example.cinemacity.domain.clients.Clients;
import com.example.cinemacity.domain.movies.Movies;
import com.example.cinemacity.domain.movies.MoviesRepository;
import com.example.cinemacity.domain.schedule.Schedule;
import com.example.cinemacity.domain.schedule.ScheduleRepository;
import com.example.cinemacity.domain.schedule.dto.ScheduleDto;
import com.example.cinemacity.exceptions.BadRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    final ScheduleRepository scheduleRepository;
    final MoviesRepository moviesRepository;
    final CinemaRoomRepository cinemaRoomRepository;

    public ScheduleService(ScheduleRepository scheduleRepository,
                           MoviesRepository moviesRepository,
                           CinemaRoomRepository cinemaRoomRepository) {
        this.scheduleRepository = scheduleRepository;
        this.moviesRepository = moviesRepository;
        this.cinemaRoomRepository = cinemaRoomRepository;
    }

    public ResponseEntity<String> add(ScheduleDto dto) {
        var cinema = cinemaRoomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new BadRequestException("Nu exista sala de cinema selectata!"));

        var movie = moviesRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new BadRequestException("Nu exista filmul selectat!"));

        List<Schedule> schedules = scheduleRepository.findAll();

        schedules.forEach(
                schedule -> {
                    if (schedule.getRoomId().equals(dto.getRoomId()) && schedule.getMovieId().equals(dto.getMovieId())) {
                        if (dto.getStartAt().isBefore(schedule.getStartAt()) && dto.getEndAt().isAfter(schedule.getStartAt())) {
                            throw new BadRequestException("Intervalul orar este deja ocupat");
                        } else if (dto.getStartAt().isBefore(schedule.getEndAt()) && dto.getStartAt().isAfter(schedule.getStartAt())) {
                            throw new BadRequestException("Intervalul orar este deja ocupat");
                        }
                    }
                }
        );

        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(dto, schedule);
        scheduleRepository.save(schedule);

        return ResponseEntity.ok("Programul a fost adaugat cu succes!");
    }

    public ResponseEntity<String> delete(String id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Programul nu a fost identificat!"));

        scheduleRepository.delete(schedule);

        return ResponseEntity.ok("Programul a fost sters cu succes!");
    }

}
