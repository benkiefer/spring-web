package org.burgers.spring.web.mvc.example

import org.junit.Before
import org.junit.Test
import org.burgers.spring.web.domain.Movie
import org.springframework.validation.BindException
import groovy.mock.interceptor.MockFor
import org.burgers.spring.web.domain.Repository

class MovieValidatorTest {
    MovieValidator validator
    private mockRepository
    MovieCommand movie
    BindException exception

    @Before
    void setUp() {
        validator = new MovieValidator()
        mockRepository = new MockFor(Repository)
        movie = createValidMovie()
        exception = new BindException(movie, "movie")
    }

    void finalizeSetUp(){
        validator.repository = mockRepository.proxyInstance()
    }

    @Test
    void supports() {
        finalizeSetUp()
        assert validator.supports(MovieCommand)
        assert !validator.supports(String)
    }

    @Test
    void validate_title() {
        mockRepository.demand.findAllMovieTitles(){[]}
        finalizeSetUp()
        validator.validate movie, exception
        verifyErrorCount 0
    }

    @Test
    void validate_title_null_from_repository() {
        mockRepository.demand.findAllMovieTitles(){null}
        finalizeSetUp()
        validator.validate movie, exception
        verifyErrorCount 0
    }

    @Test
    void validate_invalid_title_duplicate() {
        mockRepository.demand.findAllMovieTitles(){[movie.title]}

        finalizeSetUp()

        validator.validate movie, exception

        verifyErrorCount 1
        verifyErrorForField "title", "unique.title", "Movie: ${movie.title} already exists."
    }

    @Test
    void validate_invalid_title_empty() {
        mockRepository.demand.findAllMovieTitles(){[]}
        finalizeSetUp()
        movie.title = ""
        validator.validate movie, exception

        verifyErrorCount 1
        verifyErrorForField "title", "required.title"
    }

    @Test
    void validate_null_title() {
        mockRepository.demand.findAllMovieTitles(){[]}
        finalizeSetUp()

        movie.title = null
        validator.validate(movie, exception)

        verifyErrorCount 1
        verifyErrorForField "title", "required.title"
    }

    private verifyErrorCount(int count){
        if (count != 0){
            assert exception.errorCount == count
        } else {
            assert !exception.hasErrors()
        }

    }

    private verifyErrorForField(String field, String code){
        def error = exception.getFieldError(field)
        assert error.code == code
    }

    private verifyErrorForField(String field, String code, String message){
        def error = exception.getFieldError(field)
        assert error.code == code
        assert error.defaultMessage == message
    }

    private MovieCommand createValidMovie(){
        new MovieCommand(title: "Jaws")
    }
}
