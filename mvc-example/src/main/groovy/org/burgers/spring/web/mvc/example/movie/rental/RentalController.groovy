package org.burgers.spring.web.mvc.example.movie.rental

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import org.burgers.spring.web.mvc.example.ShoppingCart
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.beans.factory.annotation.Autowired
import org.burgers.spring.web.domain.Repository
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestParam

@Controller
@SessionAttributes("cart")
@RequestMapping("/rental")
class RentalController {
    @Autowired Repository repository

    @RequestMapping(value = "/select.do", method = RequestMethod.GET)
    ModelAndView selectMovie(){
        def model = [:]
        model.cart = new ShoppingCart()
        model.movies = repository.findAllMovies()
        new ModelAndView("rental/select", model)
    }

    @RequestMapping(value = "/select.do", method = RequestMethod.POST)
    ModelAndView doSubmit(@ModelAttribute ShoppingCart shoppingCart, @RequestParam Long movieId){
        shoppingCart.addItem(repository.findById(movieId))
        new ModelAndView("rental/confirm", "cart", shoppingCart)
    }

}
