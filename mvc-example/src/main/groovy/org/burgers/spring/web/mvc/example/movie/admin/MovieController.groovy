package org.burgers.spring.web.mvc.example.movie.admin

import org.burgers.spring.web.domain.Rating
import org.burgers.spring.web.domain.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class MovieController {
    @Autowired Repository repository
    @Autowired MovieValidator validator
    @Autowired MovieFactory movieFactory

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

    @RequestMapping("/options.do")
    void options() {}

    private List getRatings() {
        Rating.collect {it.name()}
    }

}
