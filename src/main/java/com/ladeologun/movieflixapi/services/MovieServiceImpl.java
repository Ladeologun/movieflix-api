package com.ladeologun.movieflixapi.services;

import com.ladeologun.movieflixapi.dtos.MovieDto;
import com.ladeologun.movieflixapi.dtos.UpdateMovieDto;
import com.ladeologun.movieflixapi.entities.Movie;
import com.ladeologun.movieflixapi.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public UpdateMovieDto updateMovie(Integer movieID, UpdateMovieDto updateMovieDto, MultipartFile file) {
        Optional<Movie> possibleMovie = movieRepository.findByMovieId(movieID);
        if (possibleMovie.isEmpty()){
            throw new RuntimeException("movie with the id does not exists");
        }
        var movie = possibleMovie.get();
        String fileName = null;
        String posterUrl = null;
        if(file != null){
            fileService.deleteResourceFile(path, movie.getPoster());
            try {
                fileName = fileService.uploadFile(path, file);
                posterUrl = baseUrl+"/file/"+fileName;

            } catch (IOException e) {
                throw new RuntimeException("unable to upload poster");
            }
        }

        updateMovieDto.setPosterurl(posterUrl);
        updateMovieDto.setPoster(fileName);
        updateMovieDto.updateMovieFields(movie);
        Movie savedMovie = movieRepository.save(movie);

        return UpdateMovieDto.builder()
                .title(savedMovie.getTitle())
                .movieId(savedMovie.getMovieId())
                .director(savedMovie.getDirector())
                .studio(savedMovie.getStudio())
                .releaseYear(savedMovie.getReleaseYear())
                .movieCasts(savedMovie.getMovieCasts())
                .poster(savedMovie.getPoster())
                .posterurl(savedMovie.getPosterUrl())
                .build();

    }

    @Override
    public void deleteMovie(Integer movieId) {
        Optional<Movie> potentialMovie= movieRepository.findByMovieId(movieId);
        if(potentialMovie.isEmpty()){
            throw new RuntimeException("movie does not exist");
        }
        fileService.deleteResourceFile(path, potentialMovie.get().getPoster());
        movieRepository.delete(potentialMovie.get());

    }

    @Override
    public MovieDto getMovie(Integer movieId) {
        Optional<Movie> potentialMovie= movieRepository.findByMovieId(movieId);
        if(potentialMovie.isEmpty()){
            throw new RuntimeException("movie does not exiat");
        }
        var movie = potentialMovie.get();
        return MovieDto.builder()
                .title(movie.getTitle())
                .movieId(movie.getMovieId())
                .director(movie.getDirector())
                .studio(movie.getStudio())
                .releaseYear(movie.getReleaseYear())
                .movieCasts(movie.getMovieCasts())
                .poster(movie.getPoster())
                .posterurl(movie.getPosterUrl())
                .build();
    }

    @Override
    public List<MovieDto> getAllMovies() {
       List<Movie> movies = movieRepository.findAll();
       return movies.stream().map((movie)->(MovieDto.builder()
               .title(movie.getTitle())
               .director(movie.getDirector())
               .movieId(movie.getMovieId())
               .studio(movie.getStudio())
               .movieCasts(movie.getMovieCasts())
               .releaseYear(movie.getReleaseYear())
               .posterurl(movie.getPosterUrl())
               .poster(movie.getPoster())
               .build())).collect(Collectors.toList());

    }
}
