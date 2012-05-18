package org.burgers.spring.web.mvc.example.movie.rental

import org.junit.Test
import org.junit.Before
import org.burgers.spring.web.domain.Repository
import groovy.mock.interceptor.MockFor
import org.burgers.spring.web.mvc.example.ShoppingCart
import org.burgers.spring.web.domain.Movie

class RentalControllerTest {
    RentalController rentalController
    private mockRepository
    @Before
    void setUp() {
        rentalController = new RentalController()
        mockRepository = new MockFor(Repository)
    }

    void finalizeSetUp(){
        rentalController.repository = mockRepository.proxyInstance()
    }

    @Test
    void selectMovie() {
        def movies = [new Movie()]

        mockRepository.demand.findAllMovies(){ movies }

        finalizeSetUp()
        def result = rentalController.selectMovie()
        assert result.viewName == "rental/select"
        assert result.model.cart instanceof ShoppingCart
        assert result.model.movies == movies
    }

    @Test
    void doSubmit() {
        def cart = new ShoppingCart()
        def movie = new Movie()

        mockRepository.demand.findById(1){ movie }
        finalizeSetUp()

        def result = rentalController.doSubmit(cart, 1)
        assert result.viewName == "rental/confirm"
        assert result.model.cart == cart
        assert cart.rentals.contains(movie)
    }

}
