package org.burgers.spring.web.mvc.example.movie.rental

import org.burgers.spring.web.domain.Rating
import org.springframework.web.multipart.commons.CommonsMultipartFile

class MovieRental implements Serializable {
    boolean selected
    Long id
    String title
    Rating rating
}
