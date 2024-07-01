package com.ladeologun.movieflixapi.dtos;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {

    private Integer movieId;

    @NotBlank(message = "please provide movie's title")
    private String title;

    @NotBlank(message = "please provide movie's director")
    private String director;

    @NotBlank(message = "please provide movie's studio")
    private String studio;

    private Set<String> movieCasts;

    private Integer releaseYear;

    @NotBlank(message = "please provide movie's poster")
    private String poster;

    @NotBlank(message = "please provide movie's posterUrl")
    private String posterurl;
}
