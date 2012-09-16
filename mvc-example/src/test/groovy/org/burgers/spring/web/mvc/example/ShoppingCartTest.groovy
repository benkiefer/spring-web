package org.burgers.spring.web.mvc.example

import org.junit.Before
import org.junit.Test

class ShoppingCartTest {
    ShoppingCart cart

    @Before
    void setUp(){
        cart = new ShoppingCart()
    }

    @Test
    void addItem_same_item_multiple_times() {
        cart.addItem(1)
        assert cart.rentals.contains(1L)
        assert cart.rentals.size() == 1
    }

    @Test
    void getItemCount() {
        cart.addItem(1)
        assert cart.itemCount == 1
    }

    @Test
    void removeItem(){
        def cart = new ShoppingCart()
        cart.rentals << 4

        cart.removeItem(4)
        assert cart.rentals.isEmpty()
    }

    @Test
    void removeItem_not_in_cart(){
        def cart = new ShoppingCart()
        cart.rentals << 2

        cart.removeItem(4)
        assert cart.rentals.size() == 1
    }

    @Test
    void removeItem_cart_is_empty(){
        def cart = new ShoppingCart()
        cart.removeItem(4)
        assert cart.rentals.isEmpty()
    }

    @Test
    void clear(){
        cart.addItem(1)
        cart.clear()
        assert cart.itemCount == 0
    }

}
