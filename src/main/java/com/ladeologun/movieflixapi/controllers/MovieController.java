package com.ladeologun.movieflixapi.controllers;

import com.ladeologun.movieflixapi.dtos.MovieDto;
import com.ladeologun.movieflixapi.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/addMovie")
    public ResponseEntity<MovieDto> addMovie(
            @RequestPart("file")MultipartFile file,
            @RequestPart("details") String movieDetails
    ){
        System.out.println(file.getOriginalFilename());
        System.out.println(movieDetails);
        return null;
    }
}
