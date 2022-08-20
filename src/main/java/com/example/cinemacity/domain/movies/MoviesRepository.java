package com.example.cinemacity.domain.movies;

import com.example.cinemacity.domain.movies.dto.MoviesDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, String> {

    Optional<MoviesDto> findMoviesById(String id);

}
