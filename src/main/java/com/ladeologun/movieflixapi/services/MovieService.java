package com.ladeologun.movieflixapi.services;

import com.ladeologun.movieflixapi.dtos.MovieDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    MovieDto addMovie(MovieDto movieDto, MultipartFile multipartFile) throws IOException;
    MovieDto getMovie(Integer movieId);
    List<MovieDto> getAllMovies();
}
