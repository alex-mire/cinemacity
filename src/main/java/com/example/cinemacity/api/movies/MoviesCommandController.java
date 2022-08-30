package com.example.cinemacity.api.movies;

import com.example.cinemacity.domain.movies.Movies;
import com.example.cinemacity.domain.movies.MoviesRepository;
import com.example.cinemacity.domain.movies.dto.MoviesDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Transactional
@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MoviesCommandController {

    final MoviesService moviesService;

    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<String> add(@RequestBody MoviesDto moviesDto) {
        return moviesService.addMovie(moviesDto);

    }

    @PatchMapping(value = "/edit/{id}", consumes = {"application/json"})
    public ResponseEntity<String> edit(@PathVariable Long id, @RequestBody MoviesDto moviesDto) {
        return moviesService.editMovie(id, moviesDto);
    }

    @DeleteMapping(value = "/delete/{id}", consumes = {"application/json"})
    public ResponseEntity<String> edit(@PathVariable Long id) {
        return moviesService.deleteMovie(id);
    }
}
