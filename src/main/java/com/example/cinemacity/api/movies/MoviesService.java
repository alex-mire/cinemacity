package com.example.cinemacity.api.movies;

import com.example.cinemacity.domain.movies.Movies;
import com.example.cinemacity.domain.movies.MoviesRepository;
import com.example.cinemacity.domain.movies.dto.MoviesDto;
import com.example.cinemacity.exceptions.BadRequestException;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MoviesService {

    final MoviesRepository moviesRepository;

    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public ResponseEntity<String> addMovie(MoviesDto moviesDto) {
        Movies movies = new Movies();
        BeanUtils.copyProperties(moviesDto, movies);

        moviesRepository.save(movies);

        return new ResponseEntity<>("Filmul a fost adaugat cu succes!", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteMovie(Long id) {
        Movies movie = moviesRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Filmul selectat nu exista!"));

        moviesRepository.delete(movie);

        return ResponseEntity.ok("Filmul a fost sters cu succes!");
    }

    public ResponseEntity<String> editMovie(Long id, MoviesDto moviesDto) {
        Movies movieToEdit = moviesRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Filmul selectat nu exista!"));

        Movies movies = new Movies();
        BeanUtils.copyProperties(moviesDto, movies);
        movies.setId(id);
        moviesRepository.save(movies);

        return new ResponseEntity<>("Filmul a fost modificat cu succes!", HttpStatus.OK);
    }
}
