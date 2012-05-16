package org.burgers.spring.web.mvc.example;

import org.junit.Test
import org.junit.Before
import org.burgers.spring.web.domain.Movie;

public class MovieFactoryTest {
    MovieFactory factory

    @Before
    void setUp(){
        factory = new MovieFactory()
    }

    @Test
    void createFrom() {
        def command = new MovieCommand(title: "bob", rented: true)
        Movie result = factory.createFrom(command)
        assert result.title == command.title
        assert result.rented == command.rented
    }
}
