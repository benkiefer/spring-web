package org.burgers.spring.web.mvc.example.movie.admin

import org.burgers.spring.web.domain.Rating
import org.springframework.web.multipart.MultipartFile

class NewMovie {
    String title
    Rating rating
    MultipartFile image
}
