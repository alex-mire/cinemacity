package com.example.cinemacity.domain.CinemaRoom;

import com.example.cinemacity.domain.CinemaRoom.dto.CinemaRoomDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, String> {

    Optional<CinemaRoom> findCinemaRoomByRoomNumber(int roomNumber);

    @Query("""
                     select new com.example.cinemacity.domain.CinemaRoom.dto.CinemaRoomDto(cr.roomNumber, cr.rowsNumber, cr.seatsNumber)
                     from CinemaRoom cr
                     where cr.id = :id
            """)
    Optional<CinemaRoomDto> findCinemaRoomDtoById(Long id);

    @Query("""
            select new com.example.cinemacity.domain.CinemaRoom.dto.CinemaRoomDto(cr.roomNumber, cr.rowsNumber, cr.seatsNumber)
             from CinemaRoom cr
            """)
    List<CinemaRoomDto> findAllCinemaRoomDto();

    Optional<CinemaRoom> findById(Long id);

}
