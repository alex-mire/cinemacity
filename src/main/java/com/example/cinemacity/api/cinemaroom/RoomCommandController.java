package com.example.cinemacity.api.cinemaroom;

import com.example.cinemacity.domain.CinemaRoom.dto.CinemaRoomDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Transactional
@RestController
@RequestMapping("/cinemaroom")
@AllArgsConstructor
public class RoomCommandController {

    final RoomService roomService;
    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<String> add(@RequestBody CinemaRoomDto dto) {
        return roomService.add(dto);
    }

    @PatchMapping(value = "/modify/{id}", consumes = {"application/json"})
    public ResponseEntity<String> modify(@PathVariable Long id, @RequestBody CinemaRoomDto dto) {
        return roomService.modify(id, dto);
    }

    @DeleteMapping(value = "/delete/{id}", consumes = {"application/json"})
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return roomService.delete(id);
    }
}
