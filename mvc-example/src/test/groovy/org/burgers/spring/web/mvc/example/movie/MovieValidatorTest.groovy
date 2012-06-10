package org.burgers.spring.web.mvc.example.movie

import groovy.mock.interceptor.MockFor

import org.burgers.spring.web.domain.Rating
import org.burgers.spring.web.domain.Repository
import org.junit.Before
import org.junit.Test
import org.springframework.validation.BindException
import org.burgers.spring.web.mvc.example.movie.MovieValidator
import org.burgers.spring.web.mvc.example.movie.NewMovie
import org.springframework.web.multipart.MultipartFile

class MovieValidatorTest {
    MovieValidator validator
    private mockRepository
    NewMovie movie
    BindException exception
    private mockImage

    @Before
    void setUp() {
        validator = new MovieValidator()
        mockRepository = new MockFor(Repository)
        movie = createValidMovie()
        exception = new BindException(movie, "movie")
        mockImage = new MockFor(MultipartFile)
    }

    void finalizeSetUp(){
        validator.repository = mockRepository.proxyInstance()
    }

    @Test
    void supports() {
        finalizeSetUp()
        assert validator.supports(NewMovie)
        assert !validator.supports(String)
    }

    @Test
    void validate_title() {
        mockImage.demand.isEmpty(){false}
        movie.image = mockImage.proxyInstance()
        mockRepository.demand.findAllMovieTitles(){[]}
        finalizeSetUp()
        validator.validate movie, exception
        verifyErrorCount 0
    }

    @Test
    void validate_title_null_from_repository() {
        mockImage.demand.isEmpty(){false}
        movie.image = mockImage.proxyInstance()
        mockRepository.demand.findAllMovieTitles(){null}
        finalizeSetUp()
        validator.validate movie, exception
        verifyErrorCount 0
    }

    @Test
    void validate_invalid_title_duplicate() {
        mockImage.demand.isEmpty(){false}
        movie.image = mockImage.proxyInstance()
        mockRepository.demand.findAllMovieTitles(){[movie.title]}

        finalizeSetUp()

        validator.validate movie, exception

        verifyErrorCount 1
        verifyErrorForField "title", "unique.title", "Movie: ${movie.title} already exists."
    }

    @Test
    void validate_invalid_title_empty() {
        mockImage.demand.isEmpty(){false}
        movie.image = mockImage.proxyInstance()
        mockRepository.demand.findAllMovieTitles(){[]}
        finalizeSetUp()
        movie.title = ""
        validator.validate movie, exception

        verifyErrorCount 1
        verifyErrorForField "title", "required.title"
    }

    @Test
    void validate_null_title() {
        mockImage.demand.isEmpty(){false}
        movie.image = mockImage.proxyInstance()
        mockRepository.demand.findAllMovieTitles(){[]}
        finalizeSetUp()

        movie.title = null
        validator.validate(movie, exception)

        verifyErrorCount 1
        verifyErrorForField "title", "required.title"
    }

    @Test
    void validate_null_file() {
        mockImage.demand.isEmpty(){true}
        movie.image = mockImage.proxyInstance()
        mockRepository.demand.findAllMovieTitles(){[]}
        finalizeSetUp()

        validator.validate(movie, exception)

        verifyErrorCount 1
        verifyErrorForField "image", "required.image"
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

    private NewMovie createValidMovie(){
        new NewMovie(title: "Jaws", rating: Rating.G)
    }
}
