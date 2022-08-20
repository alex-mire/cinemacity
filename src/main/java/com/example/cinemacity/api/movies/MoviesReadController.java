package com.example.cinemacity.api.movies;

import com.example.cinemacity.domain.movies.Movies;
import com.example.cinemacity.domain.movies.MoviesRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MoviesReadController {

    final MoviesRepository moviesRepository;

    @GetMapping("/all")
    public List<Movies> movies(){
        return moviesRepository.findAll();
    }
}
