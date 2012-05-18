package org.burgers.spring.web.mvc.example

import org.junit.Test
import org.junit.Before
import org.burgers.spring.web.domain.Movie

class ShoppingCartTest {
    ShoppingCart cart

    @Before
    void setUp(){
        cart = new ShoppingCart()
    }

    @Test
    void addItem() {
        def movie = new Movie()
        cart.addItem(movie)
        assert cart.rentals.contains(movie)
    }

    @Test
    void getItemCount() {
        def movie = new Movie()
        cart.addItem(movie)
        assert cart.itemCount == 1
    }

    @Test
    void clear(){
        def movie = new Movie()
        cart.addItem(movie)
        cart.clear()
        assert cart.itemCount == 0
    }

}
