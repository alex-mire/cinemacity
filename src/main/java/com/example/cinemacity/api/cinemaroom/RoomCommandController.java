package com.example.cinemacity.api.cinemaroom;

import com.example.cinemacity.domain.CinemaRoom.CinemaRoom;
import com.example.cinemacity.domain.CinemaRoom.CinemaRoomRepository;
import com.example.cinemacity.domain.CinemaRoom.dto.CinemaRoomDto;
import com.example.cinemacity.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Transactional
@RestController
@RequestMapping("/cinemaroom")
@AllArgsConstructor
public class RoomCommandController {

    final CinemaRoomRepository cinemaRoomRepository;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody CinemaRoomDto dto) {

        if(!cinemaRoomRepository.findCinemaRoomByRoomNumber(dto.getRoomNumber()).isEmpty()){
            throw new BadRequestException("Numarul salii de cinema este deja asociat!");
        }

        CinemaRoom cinemaRoom = new CinemaRoom();
        BeanUtils.copyProperties(dto, cinemaRoom);

        cinemaRoomRepository.save(cinemaRoom);

        return ResponseEntity.ok("Sala de cinema a fost adaugata cu succes!");
    }

    @PatchMapping("/modify/{id}")
    public ResponseEntity<String> add(@PathVariable String id, @RequestBody CinemaRoomDto dto) {

        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Sala de cinema nu a fost identificata!"));

        if(cinemaRoomRepository.findCinemaRoomByRoomNumber(dto.getRoomNumber()).isEmpty()){
            throw new BadRequestException("Numarul salii de cinema este deja asociat!");
        }

        BeanUtils.copyProperties(dto, cinemaRoom);

        cinemaRoomRepository.save(cinemaRoom);

        return ResponseEntity.ok("Sala de cinema a fost modificata cu succes!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> add(@PathVariable String id) {

        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Sala de cinema nu a fost identificata!"));

        cinemaRoomRepository.delete(cinemaRoom);

        return ResponseEntity.ok("Sala de cinema a fost stearsa cu succes!");
    }
}
