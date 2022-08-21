package com.example.cinemacity.api.cinemaroom;

import com.example.cinemacity.domain.CinemaRoom.CinemaRoomRepository;
import com.example.cinemacity.domain.CinemaRoom.dto.CinemaRoomDto;
import com.example.cinemacity.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/cinemaroom")
@AllArgsConstructor
public class RoomReadController {

    final CinemaRoomRepository cinemaRoomRepository;

    @GetMapping("/{id}")
    public CinemaRoomDto getById(@PathVariable String id){
        return cinemaRoomRepository.findCinemaRoomById(id)
                .orElseThrow(() -> new BadRequestException("Sala de cinema nu a fost identificata!"));
    }

    @GetMapping("/all")
    public List<CinemaRoomDto> getAll(){
        return cinemaRoomRepository.findAllCinemaRoomDto();
    }

}
