package org.burgers.spring.web.mvc.example.movie

import groovy.mock.interceptor.MockFor
import org.burgers.spring.web.domain.Movie
import org.burgers.spring.web.domain.Rating
import org.burgers.spring.web.domain.Repository
import org.junit.Before
import org.junit.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.validation.BindingResult
import org.springframework.web.servlet.ModelAndView

class MovieControllerTest {
    MovieController controller
    private mockRepository
    private mockBindingResult
    private mockFactory

    @Before
    void setUp() {
        controller = new MovieController()
        mockRepository = new MockFor(Repository)
        mockBindingResult = new MockFor(BindingResult)
        mockFactory = new MockFor(MovieFactory)
    }

    void finalizeSetUp(){
        controller.repository = mockRepository.proxyInstance()
        controller.movieFactory = mockFactory.proxyInstance()
    }

    @Test
    void delete(){
        def movie = new Movie()
        mockRepository.demand.findById(1){movie}
        mockRepository.demand.delete(movie){}
        finalizeSetUp()
        def request = new MockHttpServletRequest()
        request.setParameter("movieId", "1")
        controller.delete(request)
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
        assert result.model.command instanceof NewMovieForm
        assert result.model.ratings == Rating.collect {it.name()}
    }

    @Test
    void addMovie(){
        def addedMovie = new NewMovieForm(title: "Bob")
        def movie = new Movie()

        mockBindingResult.demand.hasErrors(){false}
        mockBindingResult.demand.hasFieldErrors(){false}
        mockFactory.demand.createFrom(addedMovie){movie}

        mockRepository.demand.save(movie){}

        finalizeSetUp()

        ModelAndView result = controller.onSubmit(addedMovie, mockBindingResult.proxyInstance())
        assert result.viewName == "addMovie"
        assert result.model.title == "Bob"
        assert result.model.success
    }

    @Test
    void addMovie_with_errors(){
        def movie = new NewMovieForm(title: "Bob")

        mockBindingResult.demand.hasErrors(){true}

        finalizeSetUp()

        ModelAndView result = controller.onSubmit(movie, mockBindingResult.proxyInstance())
        assert result.viewName == "addMovie"
        assert result.model.command == movie
        assert result.model.ratings == Rating.collect {it.name()}
    }

}
