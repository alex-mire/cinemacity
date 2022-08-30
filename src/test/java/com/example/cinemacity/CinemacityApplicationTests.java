package com.example.cinemacity;

import com.example.cinemacity.domain.movies.dto.MoviesDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CinemacityApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Nested
    public class MovieTests {
        @Test
        @DisplayName("Add movie - status ok")
        public void addMovieTest() throws Exception {
            MoviesDto moviesDto = new MoviesDto();
            moviesDto.setName("Avatar");
            moviesDto.setDescription("Description");
            moviesDto.setLength("2 hours");
            moviesDto.setType("Adventure");

            mockMvc.perform(post("/movies/add")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(moviesDto)))
                    .andExpect(status().isOk());
        }

    }
}


