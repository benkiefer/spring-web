package org.burgers.spring.web.mvc.example.movie

import org.burgers.spring.web.domain.Movie
import org.burgers.spring.web.domain.Rating
import org.burgers.spring.web.mvc.example.movie.rental.MovieRentalFactory
import org.junit.Before
import org.junit.Test

class MovieRentalFactoryTest {
    MovieRentalFactory factory

    @Before
    void setUp(){
        factory = new MovieRentalFactory()
    }

    @Test
    void createFrom_movie(){
        Movie movie = new Movie(id: 1, title: "Hi", rating: Rating.G)
        def result = factory.createFrom(movie)
        assert result.id == 1
        assert result.title == "Hi"
        assert result.rating == Rating.G
        assert !result.selected
    }

    @Test
    void createFrom_movie_selected_true(){
        Movie movie = new Movie(id: 1, title: "Hi", rating: Rating.G)
        def result = factory.createFrom(movie, true)
        assert result.id == 1
        assert result.title == "Hi"
        assert result.rating == Rating.G
        assert result.selected
    }

    @Test
    void create_movie_selected_false(){
        Movie movie = new Movie(id: 1, title: "Hi", rating: Rating.G)
        def result = factory.createFrom(movie, false)
        assert result.id == 1
        assert result.title == "Hi"
        assert result.rating == Rating.G
        assert !result.selected
    }
}
