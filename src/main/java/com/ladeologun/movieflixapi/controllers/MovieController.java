package com.ladeologun.movieflixapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ladeologun.movieflixapi.dtos.MovieDto;
import com.ladeologun.movieflixapi.dtos.UpdateMovieDto;
import com.ladeologun.movieflixapi.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getMovieHandler (
            @PathVariable("movieId") Integer id
    ) {
        MovieDto movie = movieService.getMovie(id);
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> deleteMovieHandler (
            @PathVariable("movieId") Integer id
    ) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<MovieDto>> getAllMoviesHandler () {
        List<MovieDto> movies= movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @PutMapping("/{movieID}")
    public ResponseEntity<UpdateMovieDto> updateMovieHandler(
            @PathVariable("movieID") Integer movieID,
            @RequestPart("file") MultipartFile file,
            @RequestPart("details") String movieDetails
    ) throws IOException {

        MultipartFile file2 = null;
        if (!file.isEmpty()){
            file2 = file;
        }

        UpdateMovieDto updatedmovieDt0 = convertToupdateMovieDto(movieDetails);
        var savedMovie = movieService.updateMovie(movieID,updatedmovieDt0,file2);
        return new ResponseEntity<>(savedMovie, HttpStatus.OK);

    }

    private MovieDto convertToMovieDto(String movieDtoString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(movieDtoString, MovieDto.class);

    }

    private UpdateMovieDto convertToupdateMovieDto(String movieDtoString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(movieDtoString, UpdateMovieDto.class);

    }
}
