package com.ladeologun.movieflixapi.dtos;

import com.ladeologun.movieflixapi.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMovieDto {

    private Integer movieId;
    private String title;
    private String director;
    private String studio;
    private Set<String> movieCasts;
    private Integer releaseYear;
    private String poster;
    private String posterurl;

    public void updateMovieFields(Movie movie){
       if(title != null){
           movie.setTitle(title);
       }
       if(director != null){
           movie.setDirector(director);
       }
       if(studio != null){
           movie.setStudio(studio);
       }
       if(movieCasts != null){
            movie.setMovieCasts(movieCasts);
       }
       if(releaseYear != null){
            movie.setReleaseYear(releaseYear);
       }
       if(poster != null){
            movie.setPoster(poster);
       }
        if(posterurl != null){
            movie.setPosterUrl(posterurl);
        }
    }
}
