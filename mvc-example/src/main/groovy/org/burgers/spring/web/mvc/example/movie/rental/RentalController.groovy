package org.burgers.spring.web.mvc.example.movie.rental

import org.burgers.spring.web.domain.Repository
import org.burgers.spring.web.mvc.example.ShoppingCart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/rental")
class RentalController {
    @Autowired Repository repository
    @Autowired MovieRentalFactory factory

    @RequestMapping(value = "/select.do", method = RequestMethod.GET)
    ModelAndView selectMovie(HttpSession session) {
        session.setAttribute("cart", new ShoppingCart())
        def rentals = repository.findAllMovies().collect {factory.createFrom(it)}
        new ModelAndView("rental/select", "movies", new Rentals(movieRentals: rentals))
    }

    @RequestMapping(value = "/cart.do", method = RequestMethod.GET)
    String viewCart() {
        "rental/confirm"
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.GET)
    @ResponseBody addMovie(HttpServletRequest request, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart")
        cart.addItem(request.getParameter("movieId").toLong())
        cart.itemCount
    }

    @RequestMapping(value = "/remove.do", method = RequestMethod.GET)
    @ResponseBody removeMovie(HttpServletRequest request, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart")
        cart.removeItem(request.getParameter("movieId").toLong())
        cart.itemCount
    }

}
