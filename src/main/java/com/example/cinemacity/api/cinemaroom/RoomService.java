package com.example.cinemacity.api.cinemaroom;

import com.example.cinemacity.domain.CinemaRoom.CinemaRoom;
import com.example.cinemacity.domain.CinemaRoom.CinemaRoomRepository;
import com.example.cinemacity.domain.CinemaRoom.dto.CinemaRoomDto;
import com.example.cinemacity.exceptions.BadRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    final CinemaRoomRepository cinemaRoomRepository;

    public RoomService(CinemaRoomRepository cinemaRoomRepository) {
        this.cinemaRoomRepository = cinemaRoomRepository;
    }

    public ResponseEntity<String> add(CinemaRoomDto dto) {
        if(!cinemaRoomRepository.findCinemaRoomByRoomNumber(dto.getRoomNumber()).isEmpty()){
            throw new BadRequestException("Numarul salii de cinema este deja asociat!");
        }

        CinemaRoom cinemaRoom = new CinemaRoom();
        BeanUtils.copyProperties(dto, cinemaRoom);

        cinemaRoomRepository.save(cinemaRoom);

        return ResponseEntity.ok("Sala de cinema a fost adaugata cu succes!");
    }

    public ResponseEntity<String> modify(Long id, CinemaRoomDto dto) {
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new BadRequestException("Sala de cinema nu a fost identificata!"));

        if(cinemaRoomRepository.findCinemaRoomByRoomNumber(dto.getRoomNumber()).isEmpty()){
            throw new BadRequestException("Numarul salii de cinema este deja asociat!");
        }

        BeanUtils.copyProperties(dto, cinemaRoom);
        cinemaRoom.setId(id);
        cinemaRoomRepository.save(cinemaRoom);

        return ResponseEntity.ok("Sala de cinema a fost modificata cu succes!");
    }

    public ResponseEntity<String> delete(Long id) {
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new BadRequestException("Sala de cinema nu a fost identificata!"));

        cinemaRoomRepository.delete(cinemaRoom);

        return ResponseEntity.ok("Sala de cinema a fost stearsa cu succes!");
    }

}
