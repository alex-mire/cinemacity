package com.example.cinemacity.api.clients;

import com.example.cinemacity.api.cinemaroom.RoomService;
import com.example.cinemacity.domain.CinemaRoom.dto.CinemaRoomDto;
import com.example.cinemacity.domain.clients.dto.ClientDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Transactional
@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientCommandController {
    final ClientsService clientsService;

    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<String> add(@RequestBody ClientDto dto) {
        return clientsService.add(dto);
    }


    @DeleteMapping(value = "/delete/{id}", consumes = {"application/json"})
    public ResponseEntity<String> delete(@PathVariable String id) {
        return clientsService.delete(id);
    }
}
