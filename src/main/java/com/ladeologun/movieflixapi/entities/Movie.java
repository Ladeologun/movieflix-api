package com.ladeologun.movieflixapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Movie {

    @Id
    @GeneratedValue
    private Integer movieId;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "please provide movie's title")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "please provide movie's director")
    private String director;

    @Column(nullable = false)
    @NotBlank(message = "please provide movie's studio")
    private String studio;

    @ElementCollection
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCasts;

    @Column(nullable = false)
    private Integer releaseYear;

    @Column(nullable = false)
    @NotBlank(message = "please provide movie's poster")
    private String poster;

//    @Column(nullable = false)
//    @NotBlank(message = "please provide movie's poster url")
    private String posterUrl;

}
