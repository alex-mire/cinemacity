package com.example.cinemacity.api.movies;

import com.example.cinemacity.domain.movies.Movies;
import com.example.cinemacity.domain.movies.MoviesRepository;
import com.example.cinemacity.domain.movies.dto.MoviesDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@Transactional
@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MoviesCommandController {

    final MoviesRepository moviesRepository;

    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<String> add(@RequestBody MoviesDto moviesDto) {

        Movies movies = new Movies();
        BeanUtils.copyProperties(moviesDto, movies);

        moviesRepository.save(movies);

        return new ResponseEntity<>("Movie added with succes!", HttpStatus.OK);

    }
}
