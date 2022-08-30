package com.example.cinemacity.api.schedule;

import com.example.cinemacity.api.clients.ClientsService;
import com.example.cinemacity.domain.clients.dto.ClientDto;
import com.example.cinemacity.domain.schedule.dto.ScheduleDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Transactional
@RestController
@RequestMapping("/schedule")
@AllArgsConstructor
public class ScheduleCommandController {

    final ScheduleService scheduleService;

    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<String> add(@RequestBody ScheduleDto dto) {
        return scheduleService.add(dto);
    }


    @DeleteMapping(value = "/delete/{id}", consumes = {"application/json"})
    public ResponseEntity<String> delete(@PathVariable String id) {
        return scheduleService.delete(id);
    }

}
