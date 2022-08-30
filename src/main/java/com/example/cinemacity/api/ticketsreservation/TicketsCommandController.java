package com.example.cinemacity.api.ticketsreservation;

import com.example.cinemacity.api.schedule.ScheduleService;
import com.example.cinemacity.domain.schedule.dto.ScheduleDto;
import com.example.cinemacity.domain.tickets.dto.TicketsDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Transactional
@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
public class TicketsCommandController {
    final TicketsService ticketsService;

    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<String> add(@RequestBody TicketsDto dto) {
        return ticketsService.add(dto);
    }


    @DeleteMapping(value = "/delete/{id}", consumes = {"application/json"})
    public ResponseEntity<String> delete(@PathVariable String id) {
        return ticketsService.delete(id);
    }
}
