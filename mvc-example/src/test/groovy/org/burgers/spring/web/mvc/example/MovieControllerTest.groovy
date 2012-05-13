package org.burgers.spring.web.mvc.example

import groovy.mock.interceptor.MockFor
import org.burgers.spring.web.domain.Repository
import org.junit.Test
import org.burgers.spring.web.domain.Movie
import org.springframework.web.servlet.ModelAndView
import org.junit.Before

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
        def movie1 = new Movie(title: "Jaws")
        def movie2 = new Movie(title: "Jaws 2")
        def movies = [movie1, movie2]

        mockRepository.demand.save(movie1){}
        mockRepository.demand.save(movie2){}
        mockRepository.demand.findAllMovies(){movies}
        finalizeSetUp()

        ModelAndView result = controller.listMovies()
        assert result.viewName == "movieList"
        assert result.model.movies == movies
    }

}
