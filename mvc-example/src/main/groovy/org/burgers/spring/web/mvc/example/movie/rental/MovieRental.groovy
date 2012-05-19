package org.burgers.spring.web.mvc.example.movie.rental

import org.burgers.spring.web.domain.Rating

class MovieRental implements Serializable {
    boolean selected
    Long id
    String title
    Rating rating
}
