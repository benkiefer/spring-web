package org.burgers.spring.web.mvc.example.movie

import org.burgers.spring.web.domain.Rating
import org.springframework.web.multipart.MultipartFile
import org.hibernate.validator.constraints.NotEmpty

class NewMovieForm {
    @NotEmpty(message = "Title is required.")
    String title
    Rating rating
    MultipartFile image
}
