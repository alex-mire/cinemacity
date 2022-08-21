package com.example.cinemacity.api.rows;

import com.example.cinemacity.domain.CinemaRoom.CinemaRoom;
import com.example.cinemacity.domain.CinemaRoom.CinemaRoomRepository;
import com.example.cinemacity.domain.rows.Rows;
import com.example.cinemacity.domain.rows.RowsRepository;
import com.example.cinemacity.domain.rows.dto.RowsDto;
import com.example.cinemacity.domain.seats.Seats;
import com.example.cinemacity.domain.seats.SeatsRepository;
import com.example.cinemacity.domain.seats.dto.SeatDto;
import com.example.cinemacity.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/rows")
@AllArgsConstructor
public class RowsCommandController {

    final RowsRepository rowsRepository;
    final CinemaRoomRepository cinemaRoomRepository;
    final SeatsRepository seatsRepository;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody RowsDto dto) {
        CinemaRoom cinemaRoom = cinemaRoomRepository.findCinemaRoomByRoomNumber(dto.getCinemaRoomNumber())
                .orElseThrow(() -> new BadRequestException("Sala de cinema nu a fost identificata!"));

        if (rowsRepository.findRowsByRowNumber(dto.getRowNumber()).isPresent()) {
            throw new BadRequestException("Exista deja un rand cu acest numar!");
        }

        List<Rows> rows = rowsRepository.findAllByCinemaRoomNumber(dto.getCinemaRoomNumber());
        long seatsNo = rows.stream().map(Rows::getSeatsNumber).count();
        if (rows.size() < cinemaRoom.getRowsNumber()) {
            Rows row = new Rows();
            BeanUtils.copyProperties(dto, row);
            rowsRepository.save(row);
            if (((int) seatsNo + dto.getSeatsNumber()) <= cinemaRoom.getSeatsNumber()) {
                SeatDto seatDto = new SeatDto();
                BeanUtils.copyProperties(row, seatDto);
                generateSeats(seatDto);
            }
            else {
                throw new BadRequestException("Ai depasit numarul maxim de locuri disponibile !");
            }

        } else {
            throw new BadRequestException("Ai depasit numarul maxim de randuri disponibil !");
        }

        return ResponseEntity.ok("Randul a fost adaugat cu succes");
    }

    public void generateSeats(SeatDto seatDto) {
        for (int i = 0; i < seatDto.getSeatsNumber(); i++) {
            Seats seats = new Seats();
            seats.setSeatNumber(i + 1);
            seats.setAvailable(false);
            seats.setRowNumber(seatDto.getRowNumber());
            seats.setCinemaRoomNumber(seatDto.getCinemaRoomNumber());
            seatsRepository.save(seats);
        }
    }

}
