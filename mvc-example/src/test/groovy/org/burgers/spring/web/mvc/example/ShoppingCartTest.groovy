package org.burgers.spring.web.mvc.example

import org.junit.Test
import org.junit.Before
import org.burgers.spring.web.domain.Movie
import org.burgers.spring.web.mvc.example.movie.rental.MovieRental

class ShoppingCartTest {
    ShoppingCart cart

    @Before
    void setUp(){
        cart = new ShoppingCart()
    }

    @Test
    void addItem() {
        def movie = new MovieRental(id: 1)
        cart.addItem(movie)
        assert cart.rentals.contains(movie)
        cart.addItem(movie)
        assert cart.itemCount == 1
    }

    @Test
    void getItemCount() {
        def movie = new MovieRental()
        cart.addItem(movie)
        assert cart.itemCount == 1
    }

    @Test
    void clear(){
        def movie = new MovieRental()
        cart.addItem(movie)
        cart.clear()
        assert cart.itemCount == 0
    }

}
