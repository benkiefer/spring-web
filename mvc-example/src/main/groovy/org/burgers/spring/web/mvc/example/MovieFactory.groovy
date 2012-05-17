package org.burgers.spring.web.mvc.example

import org.burgers.spring.web.domain.Movie
import org.springframework.stereotype.Component
import org.burgers.spring.web.domain.Rating

@Component
class MovieFactory {
    Movie createFrom(MovieCommand command){
        new Movie(title: command.title,
                rating: Rating.valueOf(Rating, command.rating))
    }
}
