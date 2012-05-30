package org.burgers.spring.web.mvc.example.movie.rental

import groovy.mock.interceptor.MockFor
import org.burgers.spring.web.domain.Movie
import org.burgers.spring.web.domain.Repository
import org.burgers.spring.web.mvc.example.ShoppingCart
import org.junit.Before
import org.junit.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpSession

class RentalControllerTest {
    RentalController rentalController
    private mockRepository
    private mockFactory
    private mockCart

    @Before
    void setUp() {
        rentalController = new RentalController()
        mockRepository = new MockFor(Repository)
        mockFactory = new MockFor(MovieRentalFactory)
        mockCart = new MockFor(ShoppingCart)
    }

    void finalizeSetUp() {
        rentalController.repository = mockRepository.proxyInstance()
        rentalController.factory = mockFactory.proxyInstance()
    }

    @Test
    void selectMovie() {
        def movie = new Movie()
        def movies = [movie]
        def rental = new MovieRental()

        mockFactory.demand.createFrom(movie) { rental }
        mockRepository.demand.findAllMovies() { movies }

        finalizeSetUp()
        def session = new MockHttpSession()
        def result = rentalController.selectMovie(session)
        assert result.viewName == "rental/select"
        assert result.model.movies.movieRentals.contains(rental)
        assert session.getAttribute("cart") instanceof ShoppingCart
    }

    @Test
    void addMovie() {
        mockCart.demand.addItem(1) {}
        mockCart.demand.getItemCount() {1}

        def session = new MockHttpSession()
        session.setAttribute("cart", mockCart.proxyInstance())

        def request = new MockHttpServletRequest()
        request.addParameter("movieId", "1")


        finalizeSetUp()

        assert rentalController.addMovie(request, session) == 1
    }

    @Test
    void removeMovie() {
        mockCart.demand.removeItem(1) {}
        mockCart.demand.getItemCount() {0}

        def session = new MockHttpSession()
        session.setAttribute("cart", mockCart.proxyInstance())

        def request = new MockHttpServletRequest()
        request.addParameter("movieId", "1")


        finalizeSetUp()

        assert rentalController.removeMovie(request, session) == 0
    }

    @Test
    void clearCart() {
        mockCart.demand.clear() {}
        mockCart.demand.getItemCount() {0}

        def session = new MockHttpSession()
        session.setAttribute("cart", mockCart.proxyInstance())

        finalizeSetUp()

        assert rentalController.clearCart(session) == 0
    }

    @Test
    void viewCart() {
        finalizeSetUp()
        assert rentalController.viewCart() == "rental/confirm"
    }

}
