package com.example.cinemacity.domain.seats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatsRepository extends JpaRepository<Seats, String> {
    List<Seats> findAllByRowNumber(Integer rowNumber);

    Optional<Seats> findById(Long id);
}
