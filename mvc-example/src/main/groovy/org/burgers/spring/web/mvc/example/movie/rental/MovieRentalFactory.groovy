package org.burgers.spring.web.mvc.example.movie.rental

import org.burgers.spring.web.domain.Movie
import org.springframework.stereotype.Component

@Component
class   MovieRentalFactory {
    MovieRental createFrom(Movie movie){
        createFrom(movie, false)
    }

    MovieRental createFrom(Movie movie, boolean selected){
        new MovieRental(id: movie.id,
                title: movie.title,
                rating: movie.rating,
                selected: selected)
    }
}
