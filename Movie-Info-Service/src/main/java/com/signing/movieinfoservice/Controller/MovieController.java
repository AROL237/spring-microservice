package com.signing.movieinfoservice.Controller;

import com.signing.movieinfoservice.Entities.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId){
        return Movie.builder()
                .movieId(movieId)
                .name("transformer")
                .build();
    }
}
