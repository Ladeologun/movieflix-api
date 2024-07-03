package com.ladeologun.movieflixapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ladeologun.movieflixapi.dtos.MovieDto;
import com.ladeologun.movieflixapi.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/addMovie")
    public ResponseEntity<MovieDto> addMovie(
            @RequestPart("file") MultipartFile file,
            @RequestPart("details") String movieDetails
    ) throws IOException {

        var movieDt0 = convertToMovieDto(movieDetails);
        var savedMovie = movieService.addMovie(movieDt0, file);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);

    }

    private MovieDto convertToMovieDto(String movieDtoString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(movieDtoString, MovieDto.class);

    }
}
