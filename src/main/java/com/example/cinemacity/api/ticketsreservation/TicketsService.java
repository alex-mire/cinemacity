package com.example.cinemacity.api.ticketsreservation;

import com.example.cinemacity.api.schedule.ScheduleService;
import com.example.cinemacity.domain.CinemaRoom.CinemaRoom;
import com.example.cinemacity.domain.CinemaRoom.CinemaRoomRepository;
import com.example.cinemacity.domain.clients.Clients;
import com.example.cinemacity.domain.clients.ClientsRepository;
import com.example.cinemacity.domain.movies.Movies;
import com.example.cinemacity.domain.movies.MoviesRepository;
import com.example.cinemacity.domain.rows.Rows;
import com.example.cinemacity.domain.rows.RowsRepository;
import com.example.cinemacity.domain.schedule.Schedule;
import com.example.cinemacity.domain.schedule.ScheduleRepository;
import com.example.cinemacity.domain.seats.Seats;
import com.example.cinemacity.domain.seats.SeatsRepository;
import com.example.cinemacity.domain.tickets.Tickets;
import com.example.cinemacity.domain.tickets.TicketsRepository;
import com.example.cinemacity.domain.tickets.dto.TicketsDto;
import com.example.cinemacity.exceptions.BadRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketsService {

    final CinemaRoomRepository cinemaRoomRepository;
    final RowsRepository rowsRepository;
    final SeatsRepository seatsRepository;
    final MoviesRepository moviesRepository;
    final ScheduleRepository scheduleRepository;
    final TicketsRepository ticketsRepository;
    final ClientsRepository clientsRepository;

    public TicketsService(CinemaRoomRepository cinemaRoomRepository,
                          RowsRepository rowsRepository,
                          SeatsRepository seatsRepository,
                          MoviesRepository moviesRepository,
                          ScheduleRepository scheduleRepository,
                          TicketsRepository ticketsRepository,
                          ClientsRepository clientsRepository) {
        this.cinemaRoomRepository = cinemaRoomRepository;
        this.rowsRepository = rowsRepository;
        this.seatsRepository = seatsRepository;
        this.moviesRepository = moviesRepository;
        this.scheduleRepository = scheduleRepository;
        this.ticketsRepository = ticketsRepository;
        this.clientsRepository = clientsRepository;
    }

    public ResponseEntity<String> add(TicketsDto dto) {
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new BadRequestException("Sala de cinema nu exista!"));
        Rows rows = rowsRepository.findById(dto.getRowId())
                .orElseThrow(() -> new BadRequestException("Randul nu exista!"));
        Seats seats = seatsRepository.findById(dto.getSeatId())
                .orElseThrow(() -> new BadRequestException("Locul nu exista!"));
        Movies movies = moviesRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new BadRequestException("Filmul nu exista!"));
        Clients clients = clientsRepository.findById(dto.getClientId())
                .orElseThrow(() -> new BadRequestException("Clientul nu exista"));

        if(seats.isAvailable()) {
            throw new BadRequestException("Locul este indisponibil!");
        }

        List<Schedule> schedules = scheduleRepository.findAllByMovieId(dto.getMovieId());
        if(schedules.size() == 0) {
            throw new BadRequestException("Filmul nu a fost programat");
        }

        schedules.forEach(schedule -> {
            if(!dto.getStartsAt().equals(schedule.getStartAt())) {
                throw new BadRequestException("Nu exista intervalul orar selectat!");
            }
        });

        seats.setAvailable(Boolean.TRUE);
        seatsRepository.save(seats);

        Tickets tickets = new Tickets();
        BeanUtils.copyProperties(dto, tickets);
        ticketsRepository.save(tickets);

        return ResponseEntity.ok("Rezervarea a fost facuta!");
    }

    public ResponseEntity<String> delete(String id) {
        Tickets tickets = ticketsRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Rezervarea nu a fost identificat!"));

        ticketsRepository.delete(tickets);

        return ResponseEntity.ok("Rezervarea a fost stearsa cu succes!");
    }
}
