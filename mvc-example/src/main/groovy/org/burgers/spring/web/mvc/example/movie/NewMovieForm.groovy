package org.burgers.spring.web.mvc.example.movie

import org.burgers.spring.web.domain.Rating
import org.springframework.web.multipart.MultipartFile

class NewMovieForm {
    String title
    Rating rating
    MultipartFile image
}
