package org.burgers.spring.web.mvc.example

import groovy.mock.interceptor.MockFor
import org.burgers.spring.web.domain.Repository
import org.junit.Test
import org.burgers.spring.web.domain.Movie
import org.springframework.web.servlet.ModelAndView
import org.junit.Before
import org.junit.After
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult

class MovieControllerTest {
    MovieController controller
    private mockRepository
    private mockValidator = [:]
    private mockFactory
    private mockBindingResult

    @Before
    void setUp() {
        controller = new MovieController()
        mockRepository = new MockFor(Repository)
        mockFactory = new MockFor(MovieFactory)
        mockBindingResult = new MockFor(BindingResult)
    }

    void finalizeSetUp(){
        controller.repository = mockRepository.proxyInstance()
        controller.factory = mockFactory.proxyInstance()
        controller.validator = mockValidator as MovieValidator
    }

    @Test
    void movieList(){
        def movies = []
        mockRepository.demand.findAllMovies(){movies}
        finalizeSetUp()

        ModelAndView result = controller.listMovies()
        assert result.viewName == "movieList"
        assert result.model.movies == movies
    }

    @Test
    void add(){
        finalizeSetUp()
        ModelAndView result = controller.add()
        assert result.viewName == "addMovie"
        assert result.model.command.class == MovieCommand
    }

    @Test
    void addMovie(){
        def movieCommand = new MovieCommand(title: "Bob")
        def movie = new Movie()

        mockBindingResult.demand.hasErrors(){false}
        mockBindingResult.demand.hasFieldErrors(){false}

        mockFactory.demand.createFrom(movieCommand){movie}
        mockRepository.demand.save(movie){}
        mockValidator = [validate: {arg1, arg2 ->
            assert arg1 == movieCommand
        }]

        finalizeSetUp()

        ModelAndView result = controller.onSubmit(movieCommand, mockBindingResult.proxyInstance())
        assert result.viewName == "success"
        assert result.model.title == "Bob"
    }

    @Test
    void addMovie_with_errors(){
        def movieCommand = new MovieCommand(title: "Bob")

        mockBindingResult.demand.hasErrors(){true}

        mockValidator = [validate: {arg1, arg2 ->
            assert arg1 == movieCommand
        }]

        finalizeSetUp()

        ModelAndView result = controller.onSubmit(movieCommand, mockBindingResult.proxyInstance())
        assert result.viewName == "addMovie"
        assert result.model.command == movieCommand
    }

}
