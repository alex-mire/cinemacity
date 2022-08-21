package com.example.cinemacity.domain.rows;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RowsRepository extends JpaRepository<Rows, String> {

    Optional<Rows> findRowsByRowNumber(Integer rowNumber);

    List<Rows> findAllByCinemaRoomNumber(Integer cinemaRoomId);

}
