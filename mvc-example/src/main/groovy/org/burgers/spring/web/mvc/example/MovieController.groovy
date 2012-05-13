package org.burgers.spring.web.mvc.example

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.beans.factory.annotation.Autowired
import org.burgers.spring.web.domain.Repository
import org.burgers.spring.web.domain.Movie
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.validation.BindingResult

@Controller
class MovieController {
    @Autowired Repository repository

    @RequestMapping("/list.do")
    ModelAndView listMovies() {
        new ModelAndView("movieList", [movies: repository.findAllMovies()])
    }

    @RequestMapping(value = "/addMovie.do", method = RequestMethod.POST)
    ModelAndView addMovie(@ModelAttribute("movie") Movie movie, BindingResult result) {
        repository.save(new Movie(title: movie.title))
        new ModelAndView("success", [title: movie.title])
    }

    @RequestMapping("/add.do")
    ModelAndView add(){
        new ModelAndView("addMovie", "command", new Movie())
    }
}
