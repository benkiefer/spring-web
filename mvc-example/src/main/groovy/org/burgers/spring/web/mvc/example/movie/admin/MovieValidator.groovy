package org.burgers.spring.web.mvc.example.movie.admin

import org.burgers.spring.web.domain.Movie
import org.burgers.spring.web.domain.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

@Component
class MovieValidator implements Validator {

    @Autowired
    Repository repository

    boolean supports(Class<?> aClass) {
        return NewMovie.isAssignableFrom(aClass);
    }

    void validate(Object target, Errors errors) {
        NewMovie movie = (NewMovie) target
        if (repository.findAllMovieTitles()?.contains(movie.title)){
                    errors.rejectValue("title", "unique.title", "Movie: $movie.title already exists.")
        }

        if (movie.image.isEmpty()){
            errors.rejectValue("image", "required.image")
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required.title")
    }
}
