package com.example.cinemacity.domain.CinemaRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRoomInterface extends JpaRepository<CinemaRoom, String> {
}
