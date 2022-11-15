package com.signing.moviecatalogueservice.Controller;

import com.signing.moviecatalogueservice.Entities.CatalogItem;
import com.signing.moviecatalogueservice.Entities.Movie;
import com.signing.moviecatalogueservice.Entities.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable  String userId){

        //get all rated movie id
        UserRating userRating = restTemplate.getForObject("http://rating-data-service/ratingsdata/"+userId, UserRating.class);

        // and for each movie id , call the info service and get the details

        //put them all together
         return userRating.getRatingList().stream().map(rating -> {
            Movie movie =restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(),Movie.class);

             //async call using web client
//             Movie movie = webClientBuilder.build()
//                     .get()
//                     .uri("http://localhost:8081/movies/\"+rating.getMovieId()")
//                     .retrieve()
//                     .bodyToMono(Movie.class)
//                     .block();

            return CatalogItem.builder().name(movie.getName()).rating(rating.getRating()).desc(movie.getDesc()).build();
        }).collect(Collectors.toList());

    }
}
