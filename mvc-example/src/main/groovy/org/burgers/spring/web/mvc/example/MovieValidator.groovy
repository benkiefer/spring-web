package org.burgers.spring.web.mvc.example

import org.springframework.validation.Validator
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.burgers.spring.web.domain.Movie
import org.springframework.beans.factory.annotation.Autowired
import org.burgers.spring.web.domain.Repository
import org.springframework.validation.BeanPropertyBindingResult

@Component
class MovieValidator implements Validator {

    @Autowired
    Repository repository

    boolean supports(Class<?> aClass) {
        return MovieCommand.isAssignableFrom(aClass);
    }

    void validate(Object target, Errors errors) {
        MovieCommand movieCommand = (MovieCommand) target
        if (repository.findAllMovieTitles()?.contains(movieCommand.title)){
                    errors.rejectValue("title", "unique.title", "Movie: $movieCommand.title already exists.")
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required.title")
    }
}
