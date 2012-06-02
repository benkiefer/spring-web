package org.burgers.spring.web.mvc.example.movie.rental

import groovy.mock.interceptor.MockFor
import org.burgers.spring.web.domain.Movie
import org.burgers.spring.web.domain.Rating
import org.burgers.spring.web.domain.Repository
import org.junit.Before
import org.junit.Test
import org.springframework.validation.BindingResult
import org.springframework.web.servlet.ModelAndView

class MovieControllerTest {
    MovieController controller
    private mockRepository
    private mockValidator = [:]
    private mockBindingResult

    @Before
    void setUp() {
        controller = new MovieController()
        mockRepository = new MockFor(Repository)
        mockBindingResult = new MockFor(BindingResult)
    }

    void finalizeSetUp(){
        controller.repository = mockRepository.proxyInstance()
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
        assert result.model.command instanceof Movie
        assert result.model.ratings == Rating.collect {it.name()}
    }

    @Test
    void addMovie(){
        def movie = new Movie(title: "Bob")

        mockBindingResult.demand.hasErrors(){false}
        mockBindingResult.demand.hasFieldErrors(){false}

        mockRepository.demand.save(movie){}
        mockValidator = [validate: {arg1, arg2 -> assert arg1 == movie }]

        finalizeSetUp()

        ModelAndView result = controller.onSubmit(movie, mockBindingResult.proxyInstance())
        assert result.viewName == "addMovie"
        assert result.model.title == "Bob"
        assert result.model.success
    }

    @Test
    void addMovie_with_errors(){
        def movie = new Movie(title: "Bob")

        mockBindingResult.demand.hasErrors(){true}

        mockValidator = [validate: {arg1, arg2 ->
            assert arg1 == movie
        }]

        finalizeSetUp()

        ModelAndView result = controller.onSubmit(movie, mockBindingResult.proxyInstance())
        assert result.viewName == "addMovie"
        assert result.model.command == movie
        assert result.model.ratings == Rating.collect {it.name()}
    }

}
