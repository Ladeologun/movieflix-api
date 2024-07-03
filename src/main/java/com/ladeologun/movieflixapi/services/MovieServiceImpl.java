package com.ladeologun.movieflixapi.services;

import com.ladeologun.movieflixapi.dtos.MovieDto;
import com.ladeologun.movieflixapi.entities.Movie;
import com.ladeologun.movieflixapi.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final FileService fileService;

    @Value("${project.poster}")
    private String path;
    @Value("${base.url}")
    private String baseUrl;


    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile multipartFile) throws IOException {
        String fileName = fileService.uploadFile(path,multipartFile);
        movieDto.setPoster(fileName);

        Movie movie = Movie.builder()
                .title(movieDto.getTitle())
                .movieCasts(movieDto.getMovieCasts())
                .poster(movieDto.getPoster())
                .director(movieDto.getDirector())
                .studio(movieDto.getStudio())
                .releaseYear(movieDto.getReleaseYear())
                .build();

        Movie savedMovie = movieRepository.save(movie);
        String posterUrl = baseUrl+"/file/"+fileName;

        MovieDto  responseMovieDto = MovieDto.builder()
                .title(savedMovie.getTitle())
                .director(savedMovie.getDirector())
                .movieId(savedMovie.getMovieId())
                .studio(savedMovie.getStudio())
                .movieCasts(savedMovie.getMovieCasts())
                .releaseYear(savedMovie.getReleaseYear())
                .posterurl(posterUrl)
                .poster(savedMovie.getPoster())
                .build();

        return responseMovieDto;
    }

    @Override
    public MovieDto getMovie(Integer movieId) {
        return null;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return null;
    }
}
