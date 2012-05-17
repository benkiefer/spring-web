package org.burgers.spring.web.mvc.example;

import org.junit.Test
import org.junit.Before
import org.burgers.spring.web.domain.Movie
import org.burgers.spring.web.domain.Rating;

public class MovieFactoryTest {
    MovieFactory factory

    @Before
    void setUp(){
        factory = new MovieFactory()
    }

    @Test
    void createFrom() {
        def command = new MovieCommand(title: "bob", rented: true, rating: Rating.PG.toString())
        Movie result = factory.createFrom(command)
        assert result.title == command.title
        assert result.rented == command.rented
        assert result.rating == Rating.PG
    }
}
