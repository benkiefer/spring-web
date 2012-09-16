package org.burgers.spring.web.mvc.example.movie

import groovy.mock.interceptor.MockFor
import org.burgers.spring.web.domain.Rating
import org.junit.Before
import org.junit.Test
import org.springframework.web.multipart.MultipartFile

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
        def addedMovie = new NewMovieForm(title: "A", rating: Rating.R, image: mockMultipartFile.proxyInstance())

        def result = factory.createFrom(addedMovie)
        assert result.title == "A"
        assert result.rating == Rating.R
        assert result.image == BYTES
    }

}
