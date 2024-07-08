package com.ladeologun.movieflixapi.services;

import com.ladeologun.movieflixapi.dtos.MovieDto;
import com.ladeologun.movieflixapi.dtos.UpdateMovieDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    MovieDto addMovie(MovieDto movieDto, MultipartFile multipartFile) throws IOException;
    UpdateMovieDto updateMovie(Integer movieID, UpdateMovieDto updateMovieDto, MultipartFile file);
    void deleteMovie(Integer moveId);
    MovieDto getMovie(Integer movieId);
    List<MovieDto> getAllMovies();
}
