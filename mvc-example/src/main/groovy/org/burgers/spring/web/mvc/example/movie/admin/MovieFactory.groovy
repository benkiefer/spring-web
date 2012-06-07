package org.burgers.spring.web.mvc.example.movie.admin

import org.burgers.spring.web.domain.Movie
import org.springframework.stereotype.Component

@Component
class MovieFactory {
    Movie createFrom(NewMovie addedMovie){
        new Movie(image: addedMovie.image.bytes, title: addedMovie.title, rating: addedMovie.rating)
    }
}
