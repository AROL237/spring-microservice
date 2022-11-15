package com.signing.ratingdataservice.Controller;

import com.signing.ratingdataservice.Entities.Rating;
import com.signing.ratingdataservice.Entities.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingController {

    @RequestMapping("/{movieId}")
    public UserRating getRating(@PathVariable String movieId)
    {
        List<Rating> ratings =Arrays.asList(
                Rating.builder().movieId("B").rating(4).build(),
                Rating.builder().movieId("A").rating(3).build()
        );
        return UserRating.builder().ratingList(ratings).build();
    }
}
