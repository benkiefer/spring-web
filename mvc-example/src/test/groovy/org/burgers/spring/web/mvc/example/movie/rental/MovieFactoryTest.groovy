package org.burgers.spring.web.mvc.example.movie.rental

import org.junit.Before
import org.junit.Test
import org.burgers.spring.web.domain.Rating

import groovy.mock.interceptor.MockFor
import org.springframework.web.multipart.MultipartFile
import org.burgers.spring.web.mvc.example.movie.admin.MovieFactory
import org.burgers.spring.web.mvc.example.movie.admin.NewMovie

class MovieFactoryTest {
    public static final BYTES = [1, 2, 3] as byte[]

    MovieFactory factory
    private mockMultipartFile

    @Before
    void setUp(){
        factory = new MovieFactory()
        mockMultipartFile = new MockFor(MultipartFile)
    }

    @Test
    void createFrom(){
        mockMultipartFile.demand.getBytes(){ BYTES }
        def addedMovie = new NewMovie(title: "A", rating: Rating.R, image: mockMultipartFile.proxyInstance())

        def result = factory.createFrom(addedMovie)
        assert result.title == "A"
        assert result.rating == Rating.R
        assert result.image == BYTES
    }

}
