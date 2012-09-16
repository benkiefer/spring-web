package org.burgers.spring.web.mvc.example.movie.rental

import groovy.mock.interceptor.MockFor
import org.burgers.spring.web.domain.Movie
import org.burgers.spring.web.domain.Repository
import org.burgers.spring.web.mvc.example.ShoppingCart
import org.junit.Before
import org.junit.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
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
    void image(){
        def response = new MockHttpServletResponse()
        mockRepository.demand.findById(1){new Movie(image: [1,2,3] as byte[])}
        finalizeSetUp()
        rentalController.image(response, 1)
        assert response.contentType == "image/jpeg"
    }

    @Test
    void selectMovie() {
        def movie = new Movie(id: 1)
        def movies = [movie]
        def rental = new MovieRental()

        mockFactory.demand.createFrom() {arg1, arg2 ->
            assert arg1 == movie
            assert !arg2
            rental
        }
        mockRepository.demand.findAllMovies() { movies }

        finalizeSetUp()
        def session = new MockHttpSession()
        def result = rentalController.selectMovie(session)
        assert result.viewName == "rental/select"
        assert result.model.movies == [[rental]]
        assert session.getAttribute("cart") instanceof ShoppingCart
    }

    @Test
    void selectMovie_existingCart_no_movies() {
        def cart = new ShoppingCart()
        def movie = new Movie(id: 1)
        def movies = [movie]
        def rental = new MovieRental()

        mockFactory.demand.createFrom() {arg1, arg2 ->
            assert arg1 == movie
            assert !arg2
            rental
        }
        mockRepository.demand.findAllMovies() { movies }

        finalizeSetUp()
        def session = new MockHttpSession()
        session.setAttribute("cart", cart)
        def result = rentalController.selectMovie(session)
        assert result.viewName == "rental/select"
        assert result.model.movies == [[rental]]
        assert session.getAttribute("cart") == cart
    }

    @Test
    void selectMovie_existingCart_with_movies() {
        def movie = new Movie(id: 1)
        def movies = [movie]
        def rental = new MovieRental()

        mockRepository.demand.findAllMovies() { movies }
        mockFactory.demand.createFrom() {arg1, arg2 ->
            assert arg1 == movie
            assert arg2
            rental
        }

        finalizeSetUp()
        def session = new MockHttpSession()
        def cart = new ShoppingCart(rentals: [1L])
        session.setAttribute("cart", cart)
        def result = rentalController.selectMovie(session)
        assert result.viewName == "rental/select"
        assert result.model.movies == [[rental]]
        assert session.getAttribute("cart") == cart
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
        def rental1 = new MovieRental()
        def rental2 = new MovieRental()
        def movie1 = new Movie()
        def movie2 = new Movie()

        mockCart.demand.getRentals(){[1, 2]}
        mockRepository.demand.findById(1){ movie1 }
        mockRepository.demand.findById(2){ movie2 }
        mockFactory.demand.createFrom(movie1){ rental1 }
        mockFactory.demand.createFrom(movie2){ rental2 }
        def session = new MockHttpSession()
        session.setAttribute("cart", mockCart.proxyInstance())
        finalizeSetUp()

        def result = rentalController.viewCart(session)
        assert result.viewName == "rental/confirm"
        assert result.model.rentals == [rental1, rental2]
    }

    @Test
    void viewCart_nothing_in_cart() {
        mockCart.demand.getRentals(){[]}
        def session = new MockHttpSession()
        session.setAttribute("cart", mockCart.proxyInstance())
        finalizeSetUp()

        def result = rentalController.viewCart(session)
        assert result.viewName == "rental/confirm"
        assert result.model.rentals == []
    }

}
