package org.burgers.spring.web.mvc.example.movie

import org.burgers.spring.web.domain.Rating
import org.burgers.spring.web.domain.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpSession
import org.burgers.spring.web.mvc.example.ShoppingCart

@Controller
@RequestMapping("/movie")
class MovieController {
    @Autowired Repository repository
    @Autowired MovieValidator validator
    @Autowired MovieFactory movieFactory


    @RequestMapping(value = "/delete.do", method = RequestMethod.GET)
    @ResponseBody delete(HttpServletRequest request) {
        def movieId = request.getParameter("movieId").toLong()
        repository.delete(repository.findById(movieId))
        ""
    }

    @RequestMapping("/list.do")
    ModelAndView listMovies() {
        new ModelAndView("movieList", [movies: repository.findAllMovies()])
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    ModelAndView onSubmit(@ModelAttribute("command") NewMovie movie, BindingResult result) {
        validator.validate(movie, result)
        if (result.hasErrors()) {
            return new ModelAndView("addMovie", [command: movie, ratings: getRatings()])
        } else {
            repository.save(movieFactory.createFrom(movie))
            return new ModelAndView("addMovie", [success: true, title: movie.title])
        }
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.GET)
    ModelAndView add() {
        new ModelAndView("addMovie", [command: new NewMovie(), ratings: getRatings()])
    }

    private List getRatings() {
        Rating.collect {it.name()}
    }

}