package org.burgers.spring.web.mvc.example

import groovy.mock.interceptor.MockFor
import org.burgers.spring.web.domain.Repository
import org.junit.Test
import org.burgers.spring.web.domain.Movie
import org.springframework.web.servlet.ModelAndView
import org.junit.Before
import org.junit.After

class MovieControllerTest {
    MovieController controller
    private mockRepository

    @Before
    void setUp() {
        controller = new MovieController()
        mockRepository = new MockFor(Repository)
    }

    void finalizeSetUp(){
        controller.repository = mockRepository.proxyInstance()
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
        assert result.model.command.class == Movie
    }

    @Test
    void addMovie(){
        def movie = new Movie(title: "Bob")
        mockRepository.demand.save(movie){}
        finalizeSetUp()
        ModelAndView result = controller.addMovie(movie, null)
        assert result.viewName == "success"
        assert result.model.title == "Bob"
    }

}
