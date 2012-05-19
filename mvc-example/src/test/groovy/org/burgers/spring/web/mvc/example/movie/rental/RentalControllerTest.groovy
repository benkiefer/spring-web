package org.burgers.spring.web.mvc.example.movie.rental

import org.junit.Test
import org.junit.Before
import org.burgers.spring.web.domain.Repository
import groovy.mock.interceptor.MockFor
import org.burgers.spring.web.mvc.example.ShoppingCart
import org.burgers.spring.web.domain.Movie

import org.springframework.mock.web.MockHttpSession
import org.springframework.validation.BindException

class RentalControllerTest {
    RentalController rentalController
    private mockRepository
    private mockFactory

    @Before
    void setUp() {
        rentalController = new RentalController()
        mockRepository = new MockFor(Repository)
        mockFactory = new MockFor(MovieRentalFactory)
    }

    void finalizeSetUp(){
        rentalController.repository = mockRepository.proxyInstance()
        rentalController.factory = mockFactory.proxyInstance()
    }

    @Test
    void selectMovie() {
        def movie = new Movie()
        def movies = [movie]
        def rental = new MovieRental()

        mockFactory.demand.createFrom(movie){ rental }
        mockRepository.demand.findAllMovies(){ movies }

        finalizeSetUp()
        def session = new MockHttpSession()
        def result = rentalController.selectMovie(session)
        assert result.viewName == "rental/select"
        assert result.model.movies.movieRentals.contains(rental)
        assert session.getAttribute("cart") instanceof ShoppingCart
    }

    @Test
    void doSubmit() {
        def session = new MockHttpSession()
        session.setAttribute("cart", new ShoppingCart())

        def selectedRental = new MovieRental(selected: true)
        def notSelectedRental = new MovieRental(selected: false)

        def rentals = new Rentals(movieRentals: [notSelectedRental, selectedRental])

        finalizeSetUp()

        def result = rentalController.onSubmit(rentals, new BindException(rentals,"rentals"), session)
        assert result.viewName == "rental/confirm"
        assert session.getAttribute("cart").rentals.contains(selectedRental)
    }

}
