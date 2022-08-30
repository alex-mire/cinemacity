package com.example.cinemacity.api.rows;

import com.example.cinemacity.domain.CinemaRoom.CinemaRoom;
import com.example.cinemacity.domain.CinemaRoom.CinemaRoomRepository;
import com.example.cinemacity.domain.rows.Rows;
import com.example.cinemacity.domain.rows.RowsRepository;
import com.example.cinemacity.domain.rows.dto.RowDeletedDto;
import com.example.cinemacity.domain.rows.dto.RowsDto;
import com.example.cinemacity.domain.seats.Seats;
import com.example.cinemacity.domain.seats.SeatsRepository;
import com.example.cinemacity.domain.seats.dto.SeatDto;
import com.example.cinemacity.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/rows")
@AllArgsConstructor
public class RowsCommandController {
    final RowService rowService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody RowsDto dto) {
        return rowService.addRow(dto);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody RowDeletedDto dto) {
        return rowService.deleteRow(dto);
    }

}
