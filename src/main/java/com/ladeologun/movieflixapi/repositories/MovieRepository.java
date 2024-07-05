package com.ladeologun.movieflixapi.repositories;

import com.ladeologun.movieflixapi.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, String> {
    Optional<Movie> findByMovieId(Integer movieId);
}
